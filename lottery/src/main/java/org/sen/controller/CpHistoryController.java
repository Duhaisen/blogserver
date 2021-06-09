package org.sen.controller;

import org.sen.bo.AnalyseCount;
import org.sen.cover.CpHistoryCover;
import org.sen.entity.CpHistory;
import org.sen.query.HistoryQuery;
import org.sen.service.CpHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CpHistoryController {

    @Autowired
    private CpHistoryService cpHistoryService;

    @RequestMapping("getLatest")
    public void getLatest(){
        cpHistoryService.saveCurrent();
    }

    @RequestMapping("/listAll")
    public List<? extends CpHistory> listAll(Boolean order) {
        if (order) {
            return CpHistoryCover.cover2WithAreaList(cpHistoryService.listAllOrdered());
        }
        return CpHistoryCover.cover2WithAreaList(cpHistoryService.listAll());
    }

    @RequestMapping("/listLately")
    public List<? extends CpHistory> listLately(@RequestParam Long quantity) {
        return CpHistoryCover.cover2WithAreaList(cpHistoryService.listLatelyOrdered(quantity));
    }

    @RequestMapping("/listByEquipmentCount")
    public List<? extends CpHistory> listByEquipmentCount(@RequestParam Integer lotteryEquipmentCount) {
        return CpHistoryCover.cover2WithAreaList(cpHistoryService.listByEquipmentCountAndOrdered(lotteryEquipmentCount));
    }

    @RequestMapping("/listByQuery")
    public List<? extends CpHistory> listByQuery(@RequestBody HistoryQuery historyQuery) {
        return CpHistoryCover.cover2WithAreaList(cpHistoryService.listPageableWithEquipmentCountAndOrdered(historyQuery));
    }

    @RequestMapping("/listLatelyAnalyse")
    public AnalyseCount listLatelyAnalyse(@RequestParam Long quantity) {
        return new AnalyseCount(CpHistoryCover.cover2WithAreaList(cpHistoryService.listLatelyOrdered(quantity)));
    }

    @RequestMapping("/listByEquipmentCountAnalyse")
    public AnalyseCount listByEquipmentCountAnalyse(@RequestParam Integer lotteryEquipmentCount) {
        return new AnalyseCount(CpHistoryCover.cover2WithAreaList(cpHistoryService.listByEquipmentCountAndOrdered(lotteryEquipmentCount)));
    }

    @RequestMapping("/listByQueryAnalyse")
    public AnalyseCount listByQueryAnalyse(@RequestBody HistoryQuery historyQuery) {
        return new AnalyseCount(CpHistoryCover.cover2WithAreaList(cpHistoryService.listPageableWithEquipmentCountAndOrdered(historyQuery)));
    }
}
