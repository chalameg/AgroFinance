package com.dxValley.AgroFinance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxValley.AgroFinance.Models.ScoringData;

public interface ScoringDataRepository extends JpaRepository<ScoringData, Long> {
    // You can add custom query methods here if needed
}
