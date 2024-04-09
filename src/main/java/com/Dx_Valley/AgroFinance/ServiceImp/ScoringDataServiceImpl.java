package com.Dx_Valley.AgroFinance.ServiceImp;

import org.springframework.stereotype.Service;

import com.Dx_Valley.AgroFinance.Enums.ScoringDataType;
import com.Dx_Valley.AgroFinance.Models.ScoringData;
import com.Dx_Valley.AgroFinance.Repository.ScoringDataRepository;
import com.Dx_Valley.AgroFinance.Service.ScoringDataService;

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
    public ScoringData createScoringData(ScoringData ScoringData) {
        return scoringDataRepository.save(ScoringData);
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

