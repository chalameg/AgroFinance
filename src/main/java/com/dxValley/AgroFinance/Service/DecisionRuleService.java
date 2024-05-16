package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.Models.DecisionRule;

import java.util.List;

public interface DecisionRuleService {

    List<DecisionRule> getAllDecisionRule();
    DecisionRule getDecisionRuleById(Long id);
    DecisionRule createDecisionRule(DecisionRule decisionRule);
    DecisionRule updateDecisionRule(Long id, DecisionRule decisionRule);
    void deleteDecisionRule(Long id);


}
