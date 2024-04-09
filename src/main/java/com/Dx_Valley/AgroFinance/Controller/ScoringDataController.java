package com.Dx_Valley.AgroFinance.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Dx_Valley.AgroFinance.Models.ScoringData;
import com.Dx_Valley.AgroFinance.Service.ScoringDataService;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/scoringData")
@AllArgsConstructor
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

