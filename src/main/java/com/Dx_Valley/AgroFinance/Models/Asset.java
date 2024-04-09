package com.Dx_Valley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String assetName;
    private String assetDescription;
    private Double assetWeight;
    private Double assetIncrement;
    private Double assetIntervalStart;
    private Double assetIntervalEnd;
    private Double assetStartValue;
    private Double assetEndValue;
    private Double assetStandard;
    private Boolean isValueIncreasing;
}
