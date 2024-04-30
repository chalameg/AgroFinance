package com.dxValley.AgroFinance.Models;

import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.annotations.*;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String description;
    
    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountDuration> accountDurations = new ArrayList<>();

    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AnnualFurtuFarmingIncome> annualFurtuFarmingIncomes = new ArrayList<>();

    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AnnulFarmingIncome> annulFarmingIncomes = new ArrayList<>();

    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AnnualNonFarmingIncome> annualNonFarmingIncomes = new ArrayList<>();

    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Asset> assets = new ArrayList<>();

    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AverageDailyBalance> averageDailyBalances = new ArrayList<>();

    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Behaviour> behaviours = new ArrayList<>();

    @OneToMany(mappedBy = "cohort", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Education> educations = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PreRemove
    private void preRemove() {
        for (AccountDuration accountDuration : accountDurations)
            accountDuration.setCohort(null);

        for (AnnualFurtuFarmingIncome annualFurtuFarmingIncome : annualFurtuFarmingIncomes)
            annualFurtuFarmingIncome.setCohort(null);

        for (AnnualNonFarmingIncome annualNonFarmingIncome : annualNonFarmingIncomes)
            annualNonFarmingIncome.setCohort(null);

        for (AnnulFarmingIncome annulFarmingIncome : annulFarmingIncomes)
            annulFarmingIncome.setCohort(null);

        for (Asset asset : assets)
            asset.setCohort(null);

        for (AverageDailyBalance averageDailyBalance : averageDailyBalances)
            averageDailyBalance.setCohort(null);
        for (Behaviour behaviour : behaviours)
            behaviour.setCohort(null);
        for (Education education : educations)
            education.setCohort(null);
    }
}
