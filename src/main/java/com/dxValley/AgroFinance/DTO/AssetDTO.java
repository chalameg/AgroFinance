package com.dxValley.AgroFinance.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssetDTO {
    @NotNull
    private Double balanceThreshold;
    @NotNull
    private Double minBalanceThreshold; 
    @NotNull
    private Double minWeight;
    @NotNull
    private String description;

    private Long cohortId;
}
