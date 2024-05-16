package com.dxValley.AgroFinance.Controller;

import com.dxValley.AgroFinance.Models.DecisionRule;
import com.dxValley.AgroFinance.Repository.DecisionRuleRepository;
import com.dxValley.AgroFinance.Service.DecisionRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/decision-rule")
@Tag(name = "Decision Rule APIS.")
@CrossOrigin(origins = {"*"}, maxAge = 3600L)
public class DecisionRuleController {
    @Autowired
    private DecisionRuleRepository decisionRuleRepository;
    @Autowired
    private DecisionRuleService decisionRuleService;
    @PostMapping
    public ResponseEntity<DecisionRule> createDecisionRule(@RequestBody DecisionRule decisionRule){
        DecisionRule decisionRule1 = decisionRuleService.createDecisionRule(decisionRule);
        return  new ResponseEntity<>(decisionRule1, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<DecisionRule>> getAllDecisionRule(){
        List<DecisionRule> decisionRuleList = decisionRuleService.getAllDecisionRule();

        return  new ResponseEntity<>(decisionRuleList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DecisionRule> getById(@PathVariable  Long id){
        DecisionRule decisionRule1 = decisionRuleService.getDecisionRuleById(id);
        if (decisionRule1==null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(decisionRule1, HttpStatus.OK);
        }
    }
    @PutMapping("/{id}")
    public  ResponseEntity<DecisionRule> updateDecisionRule(@PathVariable Long id, @RequestBody DecisionRule decisionRule){
        DecisionRule decisionRule1 = decisionRuleService.updateDecisionRule(id, decisionRule);

        if(decisionRule1==null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return  new ResponseEntity<>(decisionRule1, HttpStatus.OK);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDecisionRule(@PathVariable Long id){
        decisionRuleService.deleteDecisionRule(id);
        return  ResponseEntity.ok("Successfully deleted");


    }

}
