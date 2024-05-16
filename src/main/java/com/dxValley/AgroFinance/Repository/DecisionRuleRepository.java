package com.dxValley.AgroFinance.Repository;

import com.dxValley.AgroFinance.Models.DecisionRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionRuleRepository extends JpaRepository<DecisionRule, Long> {
    // Any custom query method
}
