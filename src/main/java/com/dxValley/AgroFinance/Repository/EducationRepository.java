package com.dxValley.AgroFinance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Models.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByCohortId(Long cohortId);

    List<Education> findByCohortIsNull();
}
