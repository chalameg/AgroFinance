package com.dxValley.AgroFinance.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FarmingAndDailyBalanceDTO {
    @NotNull
    private Double balanceThreshold;
    @NotNull
    private Double minWeight;
    @NotNull
    private String description;

    private Long cohortId;
}
