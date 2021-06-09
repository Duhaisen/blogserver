package org.sen.bo;

import org.sen.entity.CpHistoryWithArea;

import java.io.Serializable;
import java.util.*;


/**
 * 想统计的数据：
 * 1.所有数据出现的次数的排名
 * <p>
 * 2.指定那一期到现在最近一期的分析
 * <p>
 * 分析包括：
 * 1.出现次数的排名
 * 2.前端 图表
 */


public class AnalyseCount implements Serializable {

    Map<Integer, Integer> numberFrequencyOfFront;
    Map<Integer, Integer> numberFrequencyOfBack;
    Integer count;

    TreeSet<Integer> drawNumSet;

    List<CpHistoryWithArea> areaList;

    Map<Integer, TreeSet<Integer>> frontNumber2DrawNumMap;
    Map<Integer, TreeSet<Integer>> backNumber2DrawNumMap;


    public AnalyseCount() {
    }

    public AnalyseCount(List<CpHistoryWithArea> areaList) {
        this.areaList = areaList;
        initialize();
        analyse();
    }

    private void initialize() {
        this.count = this.areaList.size();
        this.numberFrequencyOfBack = new TreeMap<>();
        this.numberFrequencyOfFront = new TreeMap<>();
        this.drawNumSet = new TreeSet<>();
        this.frontNumber2DrawNumMap = new TreeMap<>();
        this.backNumber2DrawNumMap = new TreeMap<>();
    }

    private void analyse() {
        for (CpHistoryWithArea area : this.areaList) {

            this.drawNumSet.add(area.getLotteryDrawNum());

            putFront(area.getFrontArea());
            putBack(area.getBackArea());

            putFrontNumber(area.getFrontArea(), area.getLotteryDrawNum());
            putBackNumber(area.getBackArea(), area.getLotteryDrawNum());

        }
    }


    private void putFront(List<Integer> list) {
        for (Integer number : list) {
            Integer allCount = numberFrequencyOfFront.get(number);
            if (Objects.isNull(allCount)) {
                numberFrequencyOfFront.put(number, 0);
            } else {
                numberFrequencyOfFront.put(number, allCount + 1);
            }
        }
    }

    private void putBack(List<Integer> list) {
        for (Integer number : list) {
            Integer allCount = numberFrequencyOfBack.get(number);
            if (Objects.isNull(allCount)) {
                numberFrequencyOfBack.put(number, 0);
            } else {
                numberFrequencyOfBack.put(number, allCount + 1);
            }
        }
    }

    private void putFrontNumber(List<Integer> list, Integer drawNumber) {
        for (Integer number : list) {
            frontNumber2DrawNumMap.computeIfAbsent(number, k -> new TreeSet<>()).add(drawNumber);
        }
    }

    private void putBackNumber(List<Integer> list, Integer drawNumber) {
        for (Integer number : list) {
            backNumber2DrawNumMap.computeIfAbsent(number, k -> new TreeSet<>()).add(drawNumber);
        }
    }

    public Map<Integer, Integer> getNumberFrequencyOfFront() {
        return numberFrequencyOfFront;
    }

    public void setNumberFrequencyOfFront(Map<Integer, Integer> numberFrequencyOfFront) {
        this.numberFrequencyOfFront = numberFrequencyOfFront;
    }

    public Map<Integer, Integer> getNumberFrequencyOfBack() {
        return numberFrequencyOfBack;
    }

    public void setNumberFrequencyOfBack(Map<Integer, Integer> numberFrequencyOfBack) {
        this.numberFrequencyOfBack = numberFrequencyOfBack;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public TreeSet<Integer> getDrawNumSet() {
        return drawNumSet;
    }

    public void setDrawNumSet(TreeSet<Integer> drawNumSet) {
        this.drawNumSet = drawNumSet;
    }

    public List<CpHistoryWithArea> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<CpHistoryWithArea> areaList) {
        this.areaList = areaList;
    }

    public Map<Integer, TreeSet<Integer>> getFrontNumber2DrawNumMap() {
        return frontNumber2DrawNumMap;
    }

    public void setFrontNumber2DrawNumMap(Map<Integer, TreeSet<Integer>> frontNumber2DrawNumMap) {
        this.frontNumber2DrawNumMap = frontNumber2DrawNumMap;
    }

    public Map<Integer, TreeSet<Integer>> getBackNumber2DrawNumMap() {
        return backNumber2DrawNumMap;
    }

    public void setBackNumber2DrawNumMap(Map<Integer, TreeSet<Integer>> backNumber2DrawNumMap) {
        this.backNumber2DrawNumMap = backNumber2DrawNumMap;
    }

    @Override
    public String toString() {
        return "AnalyseCount{" +
                "numberFrequencyOfFront=" + numberFrequencyOfFront +
                ", numberFrequencyOfBack=" + numberFrequencyOfBack +
                ", count=" + count +
                ", drawNumSet=" + drawNumSet +
                ", areaList=" + areaList +
                ", frontNumber2DrawNumMap=" + frontNumber2DrawNumMap +
                ", backNumber2DrawNumMap=" + backNumber2DrawNumMap +
                '}';
    }
}
