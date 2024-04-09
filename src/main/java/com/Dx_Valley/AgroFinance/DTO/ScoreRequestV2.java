package com.Dx_Valley.AgroFinance.DTO;

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
    private Double annualFarmIncome;
    private Double annualNonFarmIncome;
    private Double annualFurtuFarmIncome;
    private Double accountAge;
    private Double farmingExperience;
    private Double asset;
    private Boolean isLiterate;
    private Boolean hasCreditHistory;
    private Boolean hasPaidRegularly;
    private Boolean hasPenalityHistory;
}
