package com.dxValley.AgroFinance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Models.FarmingExprience;

@Repository
public interface FarmingExprienceRepository extends JpaRepository<FarmingExprience, Long> {
    List<FarmingExprience> findByCohortId(Long cohortId);

    List<FarmingExprience> findByCohortIsNull();
}
