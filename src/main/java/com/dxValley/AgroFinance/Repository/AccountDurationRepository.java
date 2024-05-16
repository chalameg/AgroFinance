package com.dxValley.AgroFinance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dxValley.AgroFinance.Models.AccountDuration;

@Repository
public interface AccountDurationRepository extends JpaRepository<AccountDuration, Long> {
    List<AccountDuration> findByCohortId(Long cohortId);

    List<AccountDuration> findByCohortIsNull();
}
