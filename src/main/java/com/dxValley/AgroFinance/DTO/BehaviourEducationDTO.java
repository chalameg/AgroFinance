package com.dxValley.AgroFinance.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BehaviourEducationDTO {
    @NotNull
    private String name;
    @NotNull
    private String description;

    private Long cohortId;
}
