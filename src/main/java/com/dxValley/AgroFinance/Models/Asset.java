package com.dxValley.AgroFinance.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Double balanceThreshold; //this asset to laa percent
    private Double minBalanceThreshold; //this is min asset to laa percent
    private Double minWeight;
    private String description;

    @OneToOne
    @JoinColumn(name = "cohortId")
    @JsonIgnore
    private Cohort cohort;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}