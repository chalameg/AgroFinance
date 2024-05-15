package com.dxValley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FarmerData {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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
    private Boolean hasDefaultHistory;

    //relate to user model or from other end point get from farmer information


    public FarmerData(Double loanApplicationAmount, Double averageDailyBalance, Double accountAge,Double annualFarmIncome, Double annualFurtuFarmingIncome, double annualNonFarmingIncome,
                      double asset, double farmingExperience, boolean isLiterate, boolean hasCreditHistory,
                      boolean hasDefaultHistory, boolean hasPaidRegularly, boolean hasPenalityHistory
                      ){
        this.loanApplicationAmount= loanApplicationAmount;
        this.averageDailyBalance=averageDailyBalance;
        this.accountAge =accountAge;
        this.annualFarmIncome=annualFarmIncome;
        this.annualFurtuFarmIncome=annualFurtuFarmingIncome;
        this.annualNonFarmIncome=annualNonFarmingIncome;
        this.asset=asset;
        this.farmingExperience=farmingExperience;
        this.isLiterate=isLiterate;
        this.hasCreditHistory = hasCreditHistory;
        this.hasDefaultHistory=hasDefaultHistory;
        this.hasPaidRegularly=hasPaidRegularly;
        this.hasPenalityHistory=hasPenalityHistory;

    }

}
