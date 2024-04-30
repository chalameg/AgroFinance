package com.dxValley.AgroFinance.ServiceImp;

import org.springframework.stereotype.Service;

import com.dxValley.AgroFinance.Enums.ScoringDataType;
import com.dxValley.AgroFinance.Models.ScoringData;
import com.dxValley.AgroFinance.Repository.ScoringDataRepository;
import com.dxValley.AgroFinance.Service.ScoringDataService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoringDataServiceImpl implements ScoringDataService {

    private final ScoringDataRepository scoringDataRepository;

    @Override
    public List<ScoringData> getAllScoringData() {
        return scoringDataRepository.findAll();
    }

    @Override
    public ScoringData getScoringDataById(Long id) {
        return scoringDataRepository.findById(id).orElse(null);
    }

    @Override
    public ScoringData createScoringData(ScoringData scoringData) {
        if (scoringData.getRangeStart() == null) {
            scoringData.setRangeStart(null);
        }
        if (scoringData.getRangeEnd() == null) {
            scoringData.setRangeEnd(null);
        }
        return scoringDataRepository.save(scoringData);
    }

    @Override
    public ScoringData updateScoringData(Long id, ScoringData ScoringData) {
        ScoringData existingScoringData = scoringDataRepository.findById(id).orElse(null);
        if (existingScoringData != null) {
            existingScoringData.setScoringDataType(ScoringData.getScoringDataType());
            existingScoringData.setRangeStart(ScoringData.getRangeStart());
            existingScoringData.setRangeEnd(ScoringData.getRangeEnd());
            existingScoringData.setWeight(ScoringData.getWeight());
            return scoringDataRepository.save(existingScoringData);
        }
        return null;
    }

    @Override
    public void deleteScoringData(Long id) {
        scoringDataRepository.deleteById(id);
    }

    @Override
    public List<ScoringData> getScoringDataByType(ScoringDataType type) {
        return scoringDataRepository.findAll()
                .stream()
                .filter(data -> data.getScoringDataType() == type)
                .collect(Collectors.toList());
    }
}

