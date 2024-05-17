package com.dxValley.AgroFinance.DTO;

import lombok.Data;

@Data
public class ScoreResponse {
    private Double totalScore;
    private Double amountDecided;
    private String description;
    private String standard;
}
