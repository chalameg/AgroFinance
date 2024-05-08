package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.FarmingAndDailyBalanceDTO;
import com.dxValley.AgroFinance.Models.AnnualFurtuFarmingIncome;
import com.dxValley.AgroFinance.Service.AnnualFurtuFarmingIncomeService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/api/annualFurtuFarmingIncomes")
@Tag(name = "Annual Furtu Farming Income APIs.")
@RestController
public class AnnualFurtuFarmingIncomeController {

    private final AnnualFurtuFarmingIncomeService annualFurtuFarmingIncomeService;

    public AnnualFurtuFarmingIncomeController(AnnualFurtuFarmingIncomeService annualFurtuFarmingIncomeService) {
        this.annualFurtuFarmingIncomeService = annualFurtuFarmingIncomeService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<AnnualFurtuFarmingIncome>> getDefaultAnnualFurtuFarmingIncomes() {
        List<AnnualFurtuFarmingIncome> AnnualFurtuFarmingIncomes = annualFurtuFarmingIncomeService.getDefaultAnnualFurtuFarmingIncomes();
        return ResponseEntity.ok(AnnualFurtuFarmingIncomes);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<AnnualFurtuFarmingIncome>> getCohortAnnualFurtuFarmingIncomes(@PathVariable Long cohortId) {
        List<AnnualFurtuFarmingIncome> AnnualFurtuFarmingIncomes = annualFurtuFarmingIncomeService.getCohortAnnualFurtuFarmingIncomes(cohortId);
        return ResponseEntity.ok(AnnualFurtuFarmingIncomes);
    }

    @PostMapping
    public ResponseEntity<AnnualFurtuFarmingIncome> createAnnualFurtuFarmingIncome(@RequestBody @Valid FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualFurtuFarmingIncome createdAnnualFurtuFarmingIncome = annualFurtuFarmingIncomeService.createAnnualFurtuFarmingIncome(farmingAndDailyBalanceDTO);
        return ResponseEntity.ok(createdAnnualFurtuFarmingIncome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnualFurtuFarmingIncome> updateAnnualFurtuFarmingIncome(@PathVariable Long id, @RequestBody FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualFurtuFarmingIncome AnnualFurtuFarmingIncome = annualFurtuFarmingIncomeService.updateAnnualFurtuFarmingIncome(id, farmingAndDailyBalanceDTO);
        return ResponseEntity.ok(AnnualFurtuFarmingIncome);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAnnualFurtuFarmingIncome(@PathVariable Long id) {
        annualFurtuFarmingIncomeService.deleteAnnualFurtuFarmingIncome(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
