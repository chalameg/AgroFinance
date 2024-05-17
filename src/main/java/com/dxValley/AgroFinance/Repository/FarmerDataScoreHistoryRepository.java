package com.dxValley.AgroFinance.Repository;

import com.dxValley.AgroFinance.Models.FarmerDataScoreHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerDataScoreHistoryRepository extends JpaRepository<FarmerDataScoreHistory, Long> {
    //any custom query
}
