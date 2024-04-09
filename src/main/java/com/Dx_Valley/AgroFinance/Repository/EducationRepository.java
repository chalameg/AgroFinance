package com.Dx_Valley.AgroFinance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dx_Valley.AgroFinance.Models.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {
    Education findByLevel(String level);
}
