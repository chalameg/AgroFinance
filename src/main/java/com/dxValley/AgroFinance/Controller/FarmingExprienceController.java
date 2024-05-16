package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.AccountDurationDTO;
import com.dxValley.AgroFinance.Models.FarmingExprience;
import com.dxValley.AgroFinance.Service.FarmingExprienceService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/api/farmingExpriences")
@Tag(name = "Farming Exprience APIs.")
@RestController
public class FarmingExprienceController {

    private final FarmingExprienceService farmingExprienceService;

    public FarmingExprienceController(FarmingExprienceService farmingExprienceService) {
        this.farmingExprienceService = farmingExprienceService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<FarmingExprience>> getDefaultFarmingExpriences() {
        List<FarmingExprience> farmingExpriences = farmingExprienceService.getDefaultFarmingExpriences();
        return ResponseEntity.ok(farmingExpriences);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<FarmingExprience>> getCohortFarmingExpriences(@PathVariable Long cohortId) {
        List<FarmingExprience> farmingExpriences = farmingExprienceService.getCohortFarmingExpriences(cohortId);
        return ResponseEntity.ok(farmingExpriences);
    }

    @PostMapping
    public ResponseEntity<FarmingExprience> createFarmingExprience(@RequestBody @Valid AccountDurationDTO AccountDurationDTO) {
        FarmingExprience createdFarmingExprience = farmingExprienceService.createFarmingExprience(AccountDurationDTO);
        return ResponseEntity.ok(createdFarmingExprience);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmingExprience> updateFarmingExprience(@PathVariable Long id, @RequestBody AccountDurationDTO AccountDurationDTO) {
        FarmingExprience FarmingExprience = farmingExprienceService.updateFarmingExprience(id, AccountDurationDTO);
        return ResponseEntity.ok(FarmingExprience);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteFarmingExprience(@PathVariable Long id) {
        farmingExprienceService.deleteFarmingExprience(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
