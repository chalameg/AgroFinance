package com.dxValley.AgroFinance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Models.Cohort;

import java.util.List;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, Long> {

    List<Cohort> findAllByOrderByName();
}
