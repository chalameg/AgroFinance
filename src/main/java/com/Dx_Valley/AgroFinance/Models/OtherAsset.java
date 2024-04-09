package com.Dx_Valley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OtherAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    private String weight;

    private String intervalStartName;
    private String intervalEndName;
    private Long startValue;
    private Long endValue;
    private Boolean isValueIncreasing;
    private Long stardand;
}