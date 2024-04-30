package com.dxValley.AgroFinance.DTO;

import lombok.Data;

@Data
public class CohortListResponse {
    private Long id;

    private String name;

    private String description;

    private Short maxFacilityTerm;
}
