package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.FarmingAndDailyBalanceDTO;
import com.dxValley.AgroFinance.Models.AnnualNonFarmingIncome;
import com.dxValley.AgroFinance.Service.AnnualNonFarmingIncomeService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/api/annualNonFarmingIncomes")
@Tag(name = "Annual Furtu Non Farming Inocme APIs.")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600L)

public class AnnualNonFarmingIncomeController {

    private final AnnualNonFarmingIncomeService annualNonFarmingIncomeService;

    public AnnualNonFarmingIncomeController(AnnualNonFarmingIncomeService annualNonFarmingIncomeService) {
        this.annualNonFarmingIncomeService = annualNonFarmingIncomeService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<AnnualNonFarmingIncome>> getDefaultAnnualNonFarmingIncomes() {
        List<AnnualNonFarmingIncome> AnnualNonFarmingIncomes = annualNonFarmingIncomeService.getDefaultAnnualNonFarmingIncomes();
        return ResponseEntity.ok(AnnualNonFarmingIncomes);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<AnnualNonFarmingIncome>> getCohortAnnualNonFarmingIncomes(@PathVariable Long cohortId) {
        List<AnnualNonFarmingIncome> AnnualNonFarmingIncomes = annualNonFarmingIncomeService.getCohortAnnualNonFarmingIncomes(cohortId);
        return ResponseEntity.ok(AnnualNonFarmingIncomes);
    }

    @PostMapping
    public ResponseEntity<AnnualNonFarmingIncome> createAnnualNonFarmingIncome(@RequestBody @Valid FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualNonFarmingIncome createdAnnualNonFarmingIncome = annualNonFarmingIncomeService.createAnnualNonFarmingIncome(farmingAndDailyBalanceDTO);
        return ResponseEntity.ok(createdAnnualNonFarmingIncome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnualNonFarmingIncome> updateAnnualNonFarmingIncome(@PathVariable Long id, @RequestBody FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualNonFarmingIncome AnnualNonFarmingIncome = annualNonFarmingIncomeService.updateAnnualNonFarmingIncome(id, farmingAndDailyBalanceDTO);
        return ResponseEntity.ok(AnnualNonFarmingIncome);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAnnualNonFarmingIncome(@PathVariable Long id) {
        annualNonFarmingIncomeService.deleteAnnualNonFarmingIncome(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
