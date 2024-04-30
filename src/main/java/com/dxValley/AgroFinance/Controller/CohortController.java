package com.dxValley.AgroFinance.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.CohortDTO;
import com.dxValley.AgroFinance.DTO.CohortListResponse;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Service.CohortService;

import java.util.List;

@RequestMapping("/api/cohorts")
@RestController
public class CohortController {

    private final CohortService cohortService;

    public CohortController(CohortService cohortService) {
        this.cohortService = cohortService;
    }

    @GetMapping
    public ResponseEntity<List<CohortListResponse>> getAllCohorts() {
        List<CohortListResponse> cohorts = cohortService.getAllCohorts();
        return ResponseEntity.ok(cohorts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cohort> getCohortById(@PathVariable Long id) {
        Cohort cohort = cohortService.getCohortById(id);
        return ResponseEntity.ok(cohort);
    }

    @PostMapping
    public ResponseEntity<Cohort> createCohort(@RequestBody @Validated CohortDTO cohortDTO) {
        Cohort createdCohort = cohortService.createCohort(cohortDTO);
        return ResponseEntity.ok(createdCohort);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cohort> updateCohort(@PathVariable Long id, @RequestBody CohortDTO cohortDTO) {
        Cohort cohort = cohortService.updateCohort(id, cohortDTO);
        return ResponseEntity.ok(cohort);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCohort(@PathVariable Long id) {
        cohortService.deleteCohort(id);

        return ResponseEntity.ok("Deleted!");
    }
}
