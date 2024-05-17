package com.dxValley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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
