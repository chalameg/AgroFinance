package com.dxValley.AgroFinance.DTO;

import com.dxValley.AgroFinance.Enums.ScoringDataType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class WeightDTO {
    @NotNull
    private ScoringDataType scoringDataType;
    @NotNull
    private Long weight;
}
