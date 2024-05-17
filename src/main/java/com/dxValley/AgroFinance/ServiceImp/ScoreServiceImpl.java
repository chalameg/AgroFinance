package com.dxValley.AgroFinance.ServiceImp;

import com.dxValley.AgroFinance.Models.Score;
import com.dxValley.AgroFinance.Repository.ScoreRepository;
import com.dxValley.AgroFinance.Service.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private  final ScoreRepository scoreRepository;
    
    @Override
    public List<Score> getAllScore() {
        return scoreRepository.findAll();
    }

    @Override
    public Score getScoreById(Long id) {
        return scoreRepository.findById(id).orElse(null);
    }

    @Override
    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public Score updateScore(Long id, Score score) {
        Score score1 = scoreRepository.findById(id).orElse(null);
        if(score1==null){
            return  null;
        }
        
        else{
       score1.setScore(score.getScore());

            return scoreRepository.save(score1);

        }
    }

    @Override
    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);

    }
}
