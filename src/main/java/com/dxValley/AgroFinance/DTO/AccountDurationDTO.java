package com.dxValley.AgroFinance.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDurationDTO {
    @NotNull
    private Double maxMonth;
    @NotNull
    private Double minMonth;
    @NotNull
    private Double minWeight;
    @NotNull
    private String description;

    private Long cohortId;
}
