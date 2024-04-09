package com.Dx_Valley.AgroFinance.Repository;

import com.Dx_Valley.AgroFinance.Models.FarmerAge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerAgeRepository extends JpaRepository<FarmerAge, Long>{
    FarmerAge findByAgeIntervalStartLessThanEqualAndAgeIntervalEndGreaterThanEqual(Double age1, Double age2);
}
