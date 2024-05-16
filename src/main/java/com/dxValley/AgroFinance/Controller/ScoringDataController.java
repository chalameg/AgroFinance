package com.dxValley.AgroFinance.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.Models.ScoringData;
import com.dxValley.AgroFinance.Service.ScoringDataService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/scoringData")
@Tag(name = "Scoring data APIs.")
@AllArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 3600L)

public class ScoringDataController {

    private final ScoringDataService scoringDataService;

    @GetMapping
    public ResponseEntity<List<ScoringData>> getAllScoringData() {
        List<ScoringData> scoringDataList = scoringDataService.getAllScoringData();
        return new ResponseEntity<>(scoringDataList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoringData> getScoringDataById(@PathVariable Long id) {
        ScoringData scoringData = scoringDataService.getScoringDataById(id);
        if (scoringData != null) {
            return new ResponseEntity<>(scoringData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ScoringData> createScoringData(@RequestBody ScoringData scoringData) {
        ScoringData createdScoringData = scoringDataService.createScoringData(scoringData);
        return new ResponseEntity<>(createdScoringData, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScoringData> updateScoringData(@PathVariable Long id, @RequestBody ScoringData scoringData) {
        ScoringData updatedScoringData = scoringDataService.updateScoringData(id, scoringData);
        if (updatedScoringData != null) {
            return new ResponseEntity<>(updatedScoringData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScoringData(@PathVariable Long id) {
        scoringDataService.deleteScoringData(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

