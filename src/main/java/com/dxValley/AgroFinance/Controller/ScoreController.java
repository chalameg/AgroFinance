package com.dxValley.AgroFinance.Controller;

import com.dxValley.AgroFinance.Models.Score;
import com.dxValley.AgroFinance.Service.ScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/score")
@AllArgsConstructor
@Tag(name = "Score APIs.")
@CrossOrigin(origins = {"*"}, maxAge = 3600L)
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody Score score){
        Score  score1 = scoreService.createScore(score);
//        System.out.println("ghhhhhhhhhhhhhhhhhhhh"+ score1);
        return new ResponseEntity<>(score1, HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<Score>>getAllScore(){

        List<Score> scoreList = scoreService.getAllScore();
        return new ResponseEntity<>(scoreList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Long id){
        Score score1 = scoreService.getScoreById(id);
        if (score1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {

            return  new ResponseEntity<>(score1, HttpStatus.OK);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Score> updateScore(@PathVariable Long id, @RequestBody Score score){
        Score score1 = scoreService.updateScore(id, score);

        if (score1==null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        else {
            return  new ResponseEntity<>(score1, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable Long id){
        scoreService.deleteScore(id);
        return  ResponseEntity.ok("Successfully deleted");


    }

}
