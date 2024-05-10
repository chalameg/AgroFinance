package com.dxValley.AgroFinance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxValley.AgroFinance.Models.Behaviour;

@Repository
public interface BehaviourRepository extends JpaRepository<Behaviour, Long> {
    List<Behaviour> findByCohortId(Long cohortId);

    List<Behaviour> findByCohortIsNull();
}
