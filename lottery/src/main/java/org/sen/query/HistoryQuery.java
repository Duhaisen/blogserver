package org.sen.query;

public class HistoryQuery {

    private Integer lotteryEquipmentCount;

    private Integer page;

    private Integer size;


    public Integer getLotteryEquipmentCount() {
        return lotteryEquipmentCount;
    }

    public void setLotteryEquipmentCount(Integer lotteryEquipmentCount) {
        this.lotteryEquipmentCount = lotteryEquipmentCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "HistoryQuery{" +
                "lotteryEquipmentCount=" + lotteryEquipmentCount +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
