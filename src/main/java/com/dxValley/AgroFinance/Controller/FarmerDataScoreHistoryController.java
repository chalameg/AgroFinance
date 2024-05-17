package com.dxValley.AgroFinance.Controller;

import com.dxValley.AgroFinance.Models.FarmerDataScoreHistory;
import com.dxValley.AgroFinance.Service.FarmerDataScoreHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/farmerDataScoreHistories")
@AllArgsConstructor
@Tag(name = "FarmerDataScoreHistory APIs.")
public class FarmerDataScoreHistoryController {
    @Autowired
    private FarmerDataScoreHistoryService scoreService;

    @PostMapping
    public ResponseEntity<FarmerDataScoreHistory> createScore(@RequestBody FarmerDataScoreHistory score) {
        FarmerDataScoreHistory score1 = scoreService.createScore(score);
        return new ResponseEntity<>(score1, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<FarmerDataScoreHistory>> getAllScore() {

        List<FarmerDataScoreHistory> scoreList = scoreService.getAllScore();
        return new ResponseEntity<>(scoreList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmerDataScoreHistory> getScoreById(@PathVariable Long id) {
        FarmerDataScoreHistory score1 = scoreService.getScoreById(id);
        if (score1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(score1, HttpStatus.OK);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable Long id) {
        scoreService.deleteScore(id);
        return ResponseEntity.ok("Successfully deleted");

    }

}
