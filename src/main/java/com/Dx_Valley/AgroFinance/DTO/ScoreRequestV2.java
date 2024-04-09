package com.Dx_Valley.AgroFinance.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRequestV2 {
    private Long LOANAPPLICATIONAMOUNT;
    private Long AVERAGEDAILYBALANCE;
    private Long ANNUALFARMINCOME;   
    private Long ANNUALNONFARMINCOME;
    private Long ANNUALFURTUFARMINCOME;
    private Long ACCOUNTAGE;
    private Long FARMINGEXPERIENCE;
    private Long ASSET;
    private Boolean isLiterate;
    private Boolean hasCreditHistory;
    private Boolean hasPaidRegularly;
    private Boolean hasPenalityHistory;
}
