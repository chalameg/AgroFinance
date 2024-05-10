package com.dxValley.AgroFinance.Models;

import com.dxValley.AgroFinance.Enums.ScoringDataType;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private ScoringDataType scoringDataType;

    private Long weight;
}
