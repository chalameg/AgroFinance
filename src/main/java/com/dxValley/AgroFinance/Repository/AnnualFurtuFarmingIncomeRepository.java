package com.dxValley.AgroFinance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Models.AnnualFurtuFarmingIncome;

@Repository
public interface AnnualFurtuFarmingIncomeRepository extends JpaRepository<AnnualFurtuFarmingIncome, Long> {
    List<AnnualFurtuFarmingIncome> findByCohortId(Long cohortId);

    List<AnnualFurtuFarmingIncome> findByCohortIsNull();
}
