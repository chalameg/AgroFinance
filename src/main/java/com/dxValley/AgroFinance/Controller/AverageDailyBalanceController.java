package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.FarmingAndDailyBalanceDTO;
import com.dxValley.AgroFinance.Models.AverageDailyBalance;
import com.dxValley.AgroFinance.Service.AverageDailyBalanceService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/api/averageDailyBalances")
@Tag(name = "Average Daily Balance APIs.")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600L)

public class AverageDailyBalanceController {

    private final AverageDailyBalanceService averageDailyBalanceService;

    public AverageDailyBalanceController(AverageDailyBalanceService averageDailyBalanceService) {
        this.averageDailyBalanceService = averageDailyBalanceService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<AverageDailyBalance>> getDefaultAverageDailyBalances() {
        List<AverageDailyBalance> averageDailyBalances = averageDailyBalanceService.getDefaultAverageDailyBalances();
        return ResponseEntity.ok(averageDailyBalances);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<AverageDailyBalance>> getCohortAverageDailyBalances(@PathVariable Long cohortId) {
        List<AverageDailyBalance> averageDailyBalances = averageDailyBalanceService.getCohortAverageDailyBalances(cohortId);
        return ResponseEntity.ok(averageDailyBalances);
    }

    @PostMapping
    public ResponseEntity<AverageDailyBalance> createAverageDailyBalance(@RequestBody @Valid FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AverageDailyBalance createdAverageDailyBalance = averageDailyBalanceService.createAverageDailyBalance(farmingAndDailyBalanceDTO);
        return ResponseEntity.ok(createdAverageDailyBalance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AverageDailyBalance> updateAverageDailyBalance(@PathVariable Long id, @RequestBody FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AverageDailyBalance averageDailyBalance = averageDailyBalanceService.updateAverageDailyBalance(id, farmingAndDailyBalanceDTO);
        return ResponseEntity.ok(averageDailyBalance);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAverageDailyBalance(@PathVariable Long id) {
        averageDailyBalanceService.deleteAverageDailyBalance(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
