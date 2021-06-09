package org.sen.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.sen.constant.RequestConst;
import org.sen.controller.CpHistoryController;
import org.sen.dao.CpHistoryDao;
import org.sen.entity.CpHistory;
import org.sen.query.HistoryQuery;
import org.sen.service.CpHistoryService;
import org.sen.utils.HistoryRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CpHistoryServiceImpl implements CpHistoryService {

    private static final Logger log = LoggerFactory.getLogger(CpHistoryService.class);

    private static final Sort DEFAULT_SORT = Sort.by(Sort.Direction.DESC, "lotteryDrawNum");

    @Autowired
    private CpHistoryController cpHistoryController;

    @Autowired
    private CpHistoryDao dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CpHistory save(CpHistory cpHistory) {
        return dao.save(cpHistory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CpHistory> saveAll(List<CpHistory> cpHistoryList) {
        return dao.saveAll(cpHistoryList);
    }

    @Override
    public CpHistory findLastOne() {
        return dao.findFirstByOrderByLotteryDrawNumDesc();
    }

    @Override
    public List<CpHistory> listAll() {
        return dao.findAll();
    }

    @Override
    public List<CpHistory> listAllOrdered() {
        return dao.findAll(DEFAULT_SORT);
    }

    @Override
    public List<CpHistory> listLatelyOrdered(Long quantity) {
        Pageable pageable = PageRequest.of(0, quantity.intValue(), DEFAULT_SORT);
        return dao.findAll(pageable).getContent();
    }

    @Override
    public List<CpHistory> listByEquipmentCount(Integer lotteryEquipmentCount) {
        return dao.getAllByLotteryEquipmentCount(lotteryEquipmentCount);
    }

    @Override
    public List<CpHistory> listByEquipmentCountAndOrdered(Integer lotteryEquipmentCount) {
        return dao.getAllByLotteryEquipmentCount(lotteryEquipmentCount, DEFAULT_SORT);
    }

    @Override
    public List<CpHistory> listLatelyWithEquipmentCountAndOrdered(Integer lotteryEquipmentCount, Long quantity) {
        Pageable pageable = PageRequest.of(0, quantity.intValue(), DEFAULT_SORT);
        return dao.getAllByLotteryEquipmentCount(lotteryEquipmentCount, pageable);
    }

    @Override
    public List<CpHistory> listPageableWithEquipmentCountAndOrdered(HistoryQuery historyQuery) {
        Pageable pageable = PageRequest.of(historyQuery.getPage(), historyQuery.getSize(), DEFAULT_SORT);
        return dao.getAllByLotteryEquipmentCount(historyQuery.getLotteryEquipmentCount(), pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAllPast() {
        log.info("saveAllPast with maxPage = " + RequestConst.CP_HISTORY_MAX_PAGE);
        for (int i = RequestConst.CP_HISTORY_MAX_PAGE; i > 0; i--) {
            JSONObject jsonObject = HistoryRequestUtils.getRequest(RequestConst.CP_HISTORY_URL_WITH_PAGE + i);
            saveResult(jsonObject);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCurrent() {
        JSONObject jsonObject = HistoryRequestUtils.getRequest(RequestConst.CP_HISTORY_URL);
        saveResult(jsonObject);
    }

    /* 执行初始化数据库，当没有数据时 自动执行数据的插入 */
    @PostConstruct
    public void check() {
        CpHistory first = this.findLastOne();
        if (Objects.isNull(first)) {
            log.info("check is null , the db have no item");
            this.saveAllPast();
        }
    }

    private void saveResult(JSONObject jsonObject) {

        List<CpHistory> cpHistoryList = HistoryRequestUtils.result2CpHistoryList(jsonObject);

        CpHistory first = this.findLastOne();

        if (Objects.isNull(first)) {
            cpHistoryList = cpHistoryList.stream()
                    .sorted(Comparator.comparingInt(CpHistory::getLotteryDrawNum))
                    .collect(Collectors.toList());
            this.saveAll(cpHistoryList);
        } else {
            cpHistoryList = cpHistoryList.stream().filter(
                    item -> item.getLotteryDrawNum() - first.getLotteryDrawNum() > 0
            ).sorted(Comparator.comparingInt(CpHistory::getLotteryDrawNum)).collect(Collectors.toList());

            log.info("insert : " + cpHistoryList);

            if (cpHistoryList.size() > 0) {
                this.saveAll(cpHistoryList);
            }
        }
    }


}
