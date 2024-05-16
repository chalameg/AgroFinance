package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.Models.Score;

import java.util.List;

public interface ScoreService {
    List<Score> getAllScore();
    Score getScoreById(Long id);
    Score createScore(Score score);
    Score updateScore(Long id, Score score);
    void deleteScore(Long id);


}
