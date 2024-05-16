package com.dxValley.AgroFinance.Repository;

import com.dxValley.AgroFinance.Models.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    //any custom query
}
