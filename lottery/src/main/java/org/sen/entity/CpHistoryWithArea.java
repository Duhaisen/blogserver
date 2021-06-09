package org.sen.entity;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CpHistoryWithArea extends CpHistory implements Serializable {

    List<Integer> frontArea;
    List<Integer> backArea;

    public CpHistoryWithArea(){
        super();
    }

    public CpHistoryWithArea(CpHistory cpHistory){
        super();
        BeanUtils.copyProperties(cpHistory,this);

        String result = this.getLotteryDrawResult();
        String[] resultList = result.split(" ");

        frontArea = new ArrayList<>();
        backArea = new ArrayList<>();

        frontArea.add(Integer.valueOf(resultList[0]));
        frontArea.add(Integer.valueOf(resultList[1]));
        frontArea.add(Integer.valueOf(resultList[2]));
        frontArea.add(Integer.valueOf(resultList[3]));
        frontArea.add(Integer.valueOf(resultList[4]));

        backArea.add(Integer.valueOf(resultList[5]));
        backArea.add(Integer.valueOf(resultList[6]));

        frontArea.sort(Integer::compareTo);
        backArea.sort(Integer::compareTo);

    }

    public List<Integer> getFrontArea() {
        return frontArea;
    }

    public void setFrontArea(List<Integer> frontArea) {
        this.frontArea = frontArea;
    }

    public List<Integer> getBackArea() {
        return backArea;
    }

    public void setBackArea(List<Integer> backArea) {
        this.backArea = backArea;
    }

    @Override
    public String toString() {
        return "CpHistoryWithAnalyse{" +
                "frontArea=" + frontArea +
                ", backArea=" + backArea +
                '}';
    }
}
