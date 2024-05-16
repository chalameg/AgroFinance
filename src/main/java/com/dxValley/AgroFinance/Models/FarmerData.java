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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private  Long farmerAccount;
    private Double loanApplicationAmount;
    private Double averageDailyBalance;
    private Double annualFurtuFarminIncome;
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

    //relate to user by userAccount or from other end point get from farmer information


    public FarmerData(Double loanApplicationAmount,Long farmerAccount, Double averageDailyBalance, Double accountAge,Double annualFarmingIncome, Double annualFurtuFarmingIncome, double annualNonFarmingIncome,
                      double asset, double farmingExperience, boolean isLiterate, boolean hasCreditHistory,
                      boolean hasDefaultHistory, boolean hasPaidRegularly, boolean hasPenalityHistory
                      ){
        this.loanApplicationAmount= loanApplicationAmount;
        this.farmerAccount=farmerAccount;
        this.averageDailyBalance=averageDailyBalance;
        this.accountAge =accountAge;
        this.annualFarmingIncome=annualFarmingIncome;
        this.annualFurtuFarminIncome=annualFurtuFarmingIncome;
        this.annualNonFarmingIncome=annualNonFarmingIncome;
        this.asset=asset;
        this.farmingExperience=farmingExperience;
        this.isLiterate=isLiterate;
        this.hasCreditHistory = hasCreditHistory;
        this.hasDefaultHistory=hasDefaultHistory;
        this.hasPaidRegularly=hasPaidRegularly;
        this.hasPenalityHistory=hasPenalityHistory;

    }

}
