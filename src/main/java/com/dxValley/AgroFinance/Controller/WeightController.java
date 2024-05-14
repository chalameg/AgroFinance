package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.WeightDTO;
import com.dxValley.AgroFinance.Enums.ScoringDataType;
import com.dxValley.AgroFinance.Models.Weight;
import com.dxValley.AgroFinance.Service.WeightService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/weights")
@Tag(name = "Weights APIs.")
@RestController
public class WeightController {

    private final WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    @GetMapping("/{scoringDataType}")
    public ResponseEntity<Weight> getCohortWeights(@PathVariable ScoringDataType scoringDataType) {
        Weight weight = weightService.getWeight(scoringDataType);
        return ResponseEntity.ok(weight);
    }

    @GetMapping
    public ResponseEntity<List<Weight>> getWeights() {
        return ResponseEntity.ok(weightService.getWeights());
    }

    @PostMapping
    public ResponseEntity<Weight> createWeight(@RequestBody @Valid WeightDTO weightDTO) {
        Weight createdWeight = weightService.createWeight(weightDTO);
        return ResponseEntity.ok(createdWeight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Weight> updateWeight(@PathVariable Long id, @RequestBody WeightDTO weightReq) {
        Weight weight = weightService.updateWeight(id, weightReq);
        return ResponseEntity.ok(weight);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteWeight(@PathVariable Long id) {
        weightService.deleteWeight(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
