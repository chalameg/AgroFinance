package com.dxValley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private double score;
    private  Long cohortId;
    private  Long farmerAccount;
    private Double loanApplicationAmount;
    private Double averageDailyBalance;
    private Double annualFurtuFarmingIncome;
    private Double annualNonFarmingIncome;
    private Double annualFarmingIncome;
    private Double accountAge;
    private Double farmingExperience;
    private Double asset;
    private Boolean isLiterate;
    private Boolean hasCreditHistory;
    private Boolean hasPaidRegularly;
    private Boolean hasPenalityHistory;
    private Boolean hasDefaultHistory;




}
