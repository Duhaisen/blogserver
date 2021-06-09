package org.sen.cover;

import org.sen.entity.CpHistory;
import org.sen.entity.CpHistoryWithArea;

import java.util.ArrayList;
import java.util.List;

public class CpHistoryCover {

    public static List<CpHistoryWithArea> cover2WithAreaList(List<CpHistory> cpHistoryList){
        List<CpHistoryWithArea> cpHistoryWithAreaList = new ArrayList<>();
        for (CpHistory cpHistory:cpHistoryList){
            cpHistoryWithAreaList.add(new CpHistoryWithArea(cpHistory));
        }
        return cpHistoryWithAreaList;
    }

}
