package com.Dx_Valley.AgroFinance.Service;

import com.Dx_Valley.AgroFinance.Models.ScoringData;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ScoringDataService {
    List<ScoringData> getAllScoringData();
    ScoringData getScoringDataById(Long id);
    ScoringData createScoringData(ScoringData scoringData);
    ScoringData updateScoringData(Long id, ScoringData scoringData);
    void deleteScoringData(Long id);
}
