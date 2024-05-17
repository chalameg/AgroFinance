package com.dxValley.AgroFinance.ServiceImp;

import com.dxValley.AgroFinance.Models.FarmerDataScoreHistory;
import com.dxValley.AgroFinance.Repository.FarmerDataScoreHistoryRepository;
import com.dxValley.AgroFinance.Service.FarmerDataScoreHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FarmerDataScoreHistoryServiceImpl implements FarmerDataScoreHistoryService {
    private  final FarmerDataScoreHistoryRepository scoreRepository;
    
    @Override
    public List<FarmerDataScoreHistory> getAllScore() {
        return scoreRepository.findAll();
    }

    @Override
    public FarmerDataScoreHistory getScoreById(Long id) {
        return scoreRepository.findById(id).orElse(null);
    }

    @Override
    public FarmerDataScoreHistory createScore(FarmerDataScoreHistory score) {
        return scoreRepository.save(score);
    }

    @Override
    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);

    }
}
