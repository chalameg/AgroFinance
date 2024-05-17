package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.Models.FarmerDataScoreHistory;

import java.util.List;

public interface FarmerDataScoreHistoryService {
    List<FarmerDataScoreHistory> getAllScore();

    FarmerDataScoreHistory getScoreById(Long id);

    FarmerDataScoreHistory createScore(FarmerDataScoreHistory score);

    void deleteScore(Long id);

}
