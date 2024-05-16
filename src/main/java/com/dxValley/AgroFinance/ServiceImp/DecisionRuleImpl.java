package com.dxValley.AgroFinance.ServiceImp;

import com.dxValley.AgroFinance.Models.DecisionRule;
import com.dxValley.AgroFinance.Repository.DecisionRuleRepository;
import com.dxValley.AgroFinance.Service.DecisionRuleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DecisionRuleImpl implements DecisionRuleService {
    private final DecisionRuleRepository decisionRuleRepository;


    @Override
    public List<DecisionRule> getAllDecisionRule() {
        return decisionRuleRepository.findAll();
    }

    @Override
    public DecisionRule getDecisionRuleById(Long id) {
        return decisionRuleRepository.findById(id).orElse(null);
    }

    @Override
    public DecisionRule createDecisionRule(DecisionRule decisionRule) {
        return decisionRuleRepository.save(decisionRule);
    }

    @Override
    public DecisionRule updateDecisionRule(Long id, DecisionRule decisionRule) {
        DecisionRule decisionRule1 = decisionRuleRepository.findById(id).orElse(null);
        if (decisionRule1==null){

            return null;
        }

        else {
            decisionRule1.setStartValue(decisionRule.getStartValue());
            decisionRule1.setEndValue(decisionRule.getEndValue());
            decisionRule1.setAmountDecided(decisionRule.getAmountDecided());
            decisionRule1.setStandard(decisionRule.getStandard());
            decisionRule1.setDescription(decisionRule.getDescription());

            return decisionRuleRepository.save(decisionRule1);
        }

    }

    @Override
    public void deleteDecisionRule(Long id) {

    }
}
