package com.dxValley.AgroFinance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Enums.ScoringDataType;
import com.dxValley.AgroFinance.Models.Weight;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
    Weight findByScoringDataType(ScoringDataType scoringDataType);
}
