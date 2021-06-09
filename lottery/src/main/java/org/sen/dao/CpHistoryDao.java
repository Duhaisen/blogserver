package org.sen.dao;

import org.sen.entity.CpHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpHistoryDao extends JpaRepository<CpHistory,Long> {

    CpHistory findFirstByOrderByLotteryDrawNumDesc();

    List<CpHistory> getAllByLotteryEquipmentCount(Integer lotteryEquipmentCount);

    List<CpHistory> getAllByLotteryEquipmentCount(Integer lotteryEquipmentCount, Sort sort);

    List<CpHistory> getAllByLotteryEquipmentCount(Integer lotteryEquipmentCount, Pageable pageable);


}
