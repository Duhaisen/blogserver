package org.sen.service;

import org.sen.entity.CpHistory;
import org.sen.query.HistoryQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CpHistoryService {

    @CacheEvict("CpHistory")
    CpHistory save(CpHistory cpHistory);

    @CacheEvict("CpHistory")
    List<CpHistory> saveAll(List<CpHistory> cpHistoryList);


    /**
     * 得到最近一条，也就是数据库中的最后一条
     */
    @Cacheable("CpHistory")
    CpHistory findLastOne();

    /**
     * 得到所有的  默认 根据id排序的
     */
    @Cacheable("CpHistory")
    List<CpHistory> listAll();

    /**
     * 得到所有的 并根据 那一期进行继续排序，最新的在前面
     */
    @Cacheable("CpHistory")
    List<CpHistory> listAllOrdered();

    /**
     * 获取最近的几条，最新的在前面 根据那一期的进行排序的 不是根据id
     *
     * @param quantity 条目的数量 20 代表获取20条
     */
    @Cacheable(value = "CpHistory", key = "#quantity")
    List<CpHistory> listLatelyOrdered(Long quantity);

    /**
     * 根据机器号选择
     */
    @Cacheable(value = "CpHistory", key = "#lotteryEquipmentCount")
    List<CpHistory> listByEquipmentCount(Integer lotteryEquipmentCount);

    /**
     * 根据机器号选择 ， 本根据 期号倒序排序
     */
    @Cacheable(value = "CpHistory", key = "#lotteryEquipmentCount")
    List<CpHistory> listByEquipmentCountAndOrdered(Integer lotteryEquipmentCount);

    /**
     * 根据机器号获取最近的几条，最新的在前面 根据那一期的进行排序的 不是根据id
     *
     * @param quantity              条目的数量 20 代表获取20条
     * @param lotteryEquipmentCount 机器号
     */
    @Cacheable(value = "CpHistory", key = "#quantity+'-'+ #lotteryEquipmentCount")
    List<CpHistory> listLatelyWithEquipmentCountAndOrdered(Integer lotteryEquipmentCount, Long quantity);

    /**
     * 根据机器号分页查询
     */
    @Cacheable(value = "CpHistory", key = "#historyQuery.lotteryEquipmentCount")
    List<CpHistory> listPageableWithEquipmentCountAndOrdered(HistoryQuery historyQuery);

    /**
     * 插入以前的历史，默认从 71也查询，以后随着数据的增多需要扩大数据量
     */
    @CacheEvict("CpHistory")
    void saveAllPast();

    /**
     * 保存最新的数据，默认查取30条，然后插入 最后一条之后的 不存在的 数据。
     */
    @CacheEvict("CpHistory")
    void saveCurrent();


}
