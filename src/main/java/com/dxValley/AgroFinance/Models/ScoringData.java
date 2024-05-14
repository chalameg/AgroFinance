package com.dxValley.AgroFinance.Models;
import com.dxValley.AgroFinance.Enums.ScoringDataType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ScoringData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ScoringDataType scoringDataType;
    
    private Double rangeStart;
    private Double rangeEnd;
    private Double weight;
}

