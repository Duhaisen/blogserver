package org.sen.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cphistory",
        uniqueConstraints = {
                @UniqueConstraint(name = "uni-draw-num", columnNames = {"lotteryDrawNum"})
        })
public class CpHistory implements Comparable<CpHistory>, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lotteryDrawTime;
    private String lotteryDrawResult;
    private Integer lotteryDrawNum;
    private Integer lotteryEquipmentCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLotteryDrawTime() {
        return lotteryDrawTime;
    }

    public void setLotteryDrawTime(String lotteryDrawTime) {
        this.lotteryDrawTime = lotteryDrawTime;
    }

    public String getLotteryDrawResult() {
        return lotteryDrawResult;
    }

    public void setLotteryDrawResult(String lotteryDrawResult) {
        this.lotteryDrawResult = lotteryDrawResult;
    }

    public Integer getLotteryDrawNum() {
        return lotteryDrawNum;
    }

    public void setLotteryDrawNum(Integer lotteryDrawNum) {
        this.lotteryDrawNum = lotteryDrawNum;
    }

    public Integer getLotteryEquipmentCount() {
        return lotteryEquipmentCount;
    }

    public void setLotteryEquipmentCount(Integer lotteryEquipmentCount) {
        this.lotteryEquipmentCount = lotteryEquipmentCount;
    }

    @Override
    public String toString() {
        return "CpHistory{" +
                "id=" + id +
                ", lotteryDrawTime='" + lotteryDrawTime + '\'' +
                ", lotteryDrawResult='" + lotteryDrawResult + '\'' +
                ", lotteryDrawNum=" + lotteryDrawNum +
                ", lotteryEquipmentCount=" + lotteryEquipmentCount +
                '}';
    }

    @Override
    public int compareTo(CpHistory o) {
        return o.getLotteryDrawNum() - getLotteryDrawNum();
    }


}
