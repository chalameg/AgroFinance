package com.dxValley.AgroFinance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Models.AverageDailyBalance;

@Repository
public interface AverageDailyBalanceRepository extends JpaRepository<AverageDailyBalance, Long> {
    List<AverageDailyBalance> findByCohortId(Long cohortId);

    List<AverageDailyBalance> findByCohortIsNull();
}
