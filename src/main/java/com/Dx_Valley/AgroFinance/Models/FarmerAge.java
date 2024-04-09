package com.Dx_Valley.AgroFinance.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FarmerAge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Double ageIntervalStart;
    private Double ageIntervalEnd;
    private Double ageValue;
    private Double ageStandard;
    private Double weight;
}
