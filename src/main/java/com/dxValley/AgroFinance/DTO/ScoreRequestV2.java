package com.dxValley.AgroFinance.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScoreRequestV2 {
    private Double loanApplicationAmount;
    private Double averageDailyBalance;
    private Double annualFarmingIncome;
    private Double annualNonFarmingIncome;
    private Double annualFurtuFarmingIncome;
    private Double accountAge;
    private Double farmingExperience;
    private Double asset;
    private Boolean isLiterate;
    private Boolean hasCreditHistory;
    private Boolean hasPaidRegularly;
    private Boolean hasPenalityHistory;
    private Boolean hasDefaultHistory;
}
