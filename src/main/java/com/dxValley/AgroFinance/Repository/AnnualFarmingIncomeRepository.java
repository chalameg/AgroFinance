package com.dxValley.AgroFinance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Models.AnnualFarmingIncome;

@Repository
public interface AnnualFarmingIncomeRepository extends JpaRepository<AnnualFarmingIncome, Long> {
    List<AnnualFarmingIncome> findByCohortId(Long cohortId);

    List<AnnualFarmingIncome> findByCohortIsNull();
}
