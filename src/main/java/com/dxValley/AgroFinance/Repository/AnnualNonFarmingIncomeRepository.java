package com.dxValley.AgroFinance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Models.AnnualNonFarmingIncome;

@Repository
public interface AnnualNonFarmingIncomeRepository extends JpaRepository<AnnualNonFarmingIncome, Long> {
    List<AnnualNonFarmingIncome> findByCohortId(Long cohortId);

    List<AnnualNonFarmingIncome> findByCohortIsNull();
}
