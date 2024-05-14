package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.Enums.ScoringDataType;
import com.dxValley.AgroFinance.Models.ScoringData;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ScoringDataService {
    List<ScoringData> getAllScoringData();
    ScoringData getScoringDataById(Long id);
    ScoringData createScoringData(ScoringData scoringData);
    ScoringData updateScoringData(Long id, ScoringData scoringData);
    void deleteScoringData(Long id);
    List<ScoringData> getScoringDataByType(ScoringDataType type);
}
