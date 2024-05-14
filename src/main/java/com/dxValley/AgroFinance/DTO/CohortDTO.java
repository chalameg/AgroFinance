package com.dxValley.AgroFinance.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CohortDTO {
  
    @NotNull
    private String name;
    @NotNull
    private String description;
}
