package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.FarmingAndDailyBalanceDTO;
import com.dxValley.AgroFinance.Models.AnnualFarmingIncome;
import com.dxValley.AgroFinance.Service.AnnualFarmingIncomeService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/api/annualFarmingIncomes")
@Tag(name = "Annual Farming Income APIs.")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600L)

public class AnnualFarmingIncomeController {

    private final AnnualFarmingIncomeService annualFarmingIncomeService;

    public AnnualFarmingIncomeController(AnnualFarmingIncomeService annualFarmingIncomeService) {
        this.annualFarmingIncomeService = annualFarmingIncomeService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<AnnualFarmingIncome>> getDefaultAnnualFarmingIncomes() {
        List<AnnualFarmingIncome> AnnualFarmingIncomes = annualFarmingIncomeService.getDefaultAnnualFarmingIncomes();
        return ResponseEntity.ok(AnnualFarmingIncomes);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<AnnualFarmingIncome>> getCohortAnnualFarmingIncomes(@PathVariable Long cohortId) {
        List<AnnualFarmingIncome> AnnualFarmingIncomes = annualFarmingIncomeService.getCohortAnnualFarmingIncomes(cohortId);
        return ResponseEntity.ok(AnnualFarmingIncomes);
    }

    @PostMapping
    public ResponseEntity<AnnualFarmingIncome> createAnnualFarmingIncome(@RequestBody @Valid FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualFarmingIncome createdAnnualFarmingIncome = annualFarmingIncomeService.createAnnualFarmingIncome(farmingAndDailyBalanceDTO);
        return ResponseEntity.ok(createdAnnualFarmingIncome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnualFarmingIncome> updateAnnualFarmingIncome(@PathVariable Long id, @RequestBody FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualFarmingIncome AnnualFarmingIncome = annualFarmingIncomeService.updateAnnualFarmingIncome(id, farmingAndDailyBalanceDTO);
        return ResponseEntity.ok(AnnualFarmingIncome);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAnnualFarmingIncome(@PathVariable Long id) {
        annualFarmingIncomeService.deleteAnnualFarmingIncome(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
