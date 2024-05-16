package com.dxValley.AgroFinance.Controller;

import java.util.List;

import com.dxValley.AgroFinance.Models.DecisionRule;
import com.dxValley.AgroFinance.Models.Score;
import com.dxValley.AgroFinance.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.ScoreRequestV2;
import com.dxValley.AgroFinance.Enums.ScoringDataType;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Models.ScoringData;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Score calculation APIs.")
@RequestMapping("/api/score")
@CrossOrigin(origins = {"*"}, maxAge = 3600L)
public class ScoreCalculatorController {
    private final ScoringDataService scoringDataService;
    private final CohortService cohortService;
    private  final DecisionRuleService decisionRuleService;
    private final  ScoreService scoreService;
    private final WeightService weightService;
  
    @PostMapping("/calculatev2")
    public ResponseEntity<Double> calculateScorev2(@RequestBody ScoreRequestV2 request) {
        Double totalScore = 0D;
        Double laa = request.getLoanApplicationAmount();

        totalScore += calculateScoreForType(ScoringDataType.AVERAGEDAILYBALANCE, request.getAverageDailyBalance(), laa);
        totalScore += calculateScoreForType(ScoringDataType.ANNUALFARMINGINCOME, request.getAnnualFarmingIncome(), laa);
        totalScore += calculateScoreForType(ScoringDataType.ANNUALNONFARMINGINCOME, request.getAnnualNonFarmingIncome(), laa);
        // no need of laa for farming exprience and account age
        totalScore += calculateScoreForType(ScoringDataType.FARMINGEXPERIENCE, request.getFarmingExperience(), laa); 
        totalScore += calculateScoreForType(ScoringDataType.ACCOUNTAGE, request.getAccountAge(), laa);

        totalScore += calculateScoreForType(ScoringDataType.ANNUALFURTUFARMINGINCOME, request.getAnnualFurtuFarmingIncome(), laa);
        totalScore += calculateScoreForType(ScoringDataType.ASSET, request.getAsset(), laa);
        totalScore += request.getIsLiterate() ? calculateScoreForYesNoType(ScoringDataType.LITERATE) : calculateScoreForYesNoType(ScoringDataType.ILLITERATE);
        
        if(request.getHasCreditHistory() & request.getHasPaidRegularly()){
            if(!request.getHasDefaultHistory() & !request.getHasPenalityHistory()){
                totalScore += calculateScoreForYesNoType(ScoringDataType.GOODBEHAVIOUR);
            }else if(!request.getHasDefaultHistory() & request.getHasPenalityHistory()){
                totalScore += calculateScoreForYesNoType(ScoringDataType.MODERATEBEHAVIOUR);
            }else{
                totalScore += calculateScoreForYesNoType(ScoringDataType.BADBEHAVIOUR);
            }
        }else{
            totalScore += calculateScoreForYesNoType(ScoringDataType.BADBEHAVIOUR);
        }
        Score score1 = new Score();
        score1.setScore(totalScore);
        scoreService.createScore(score1);


        String[] result = getScoreAndDescription(totalScore);
        System.out.println("Score: " + totalScore + ", AmountDecided: " + result[0] + ", Description: " + result[1]+ ", Standard: " + result[2]);


        return ResponseEntity.ok(totalScore);
    }

//    private  double scoreResult(double score12){
//        List<DecisionRule> decisionRuleList = decisionRuleService.getAllDecisionRule();
//
//        for (DecisionRule range : decisionRuleList) {
//            if (range.getStartValue() <= score12 && score12 <= range.getEndValue()) {
//                return range.getAmountDecided();
//            };
//            }
////
//        return  0.0;
//
//    }

    public  String[] getScoreAndDescription(double value) {
         List<DecisionRule> decisionRuleList = decisionRuleService.getAllDecisionRule();

        for (DecisionRule range : decisionRuleList) {
            if (range.getStartValue() <= value && value <= range.getEndValue()) {
                return new String[]{String.valueOf(range.getAmountDecided()), range.getDescription(), range.getStandard()};
            }
        }
        return new String[]{null, "Out of Range"};
    }





    private Double calculateScoreForType(ScoringDataType type, Double value, Double laa) {
        List<ScoringData> scoringDataList = scoringDataService.getScoringDataByType(type);
        Double percentage;

        if(type == ScoringDataType.ACCOUNTAGE | type == ScoringDataType.FARMINGEXPERIENCE){
            percentage = value;
        }else{
            percentage = calculatePercentage(value, laa);
        }

        for (ScoringData data : scoringDataList) {
            if (percentage >= data.getRangeStart() && percentage <= data.getRangeEnd()) {
                return data.getWeight();
            }
        }
        return 0.0; // Return 0 if no matching range is found
    }

    private Double calculateScoreForYesNoType(ScoringDataType type) {
        List<ScoringData> scoringDataList = scoringDataService.getScoringDataByType(type);

        Double score = 0.0;

        for (ScoringData data : scoringDataList) {
            score+=data.getWeight();
        }
        return score;
    }


    public Double calculatePercentage(Double a, Double b) {
        if (b == 0) {
            throw new IllegalArgumentException("B cannot be zero.");
        }
        return (a / b) * 100.0;
    }
    

    @PostMapping("/calculate/{cohortId}")
    public ResponseEntity<Double> calculateScore(@RequestBody ScoreRequestV2 request, @PathVariable Long cohortId) {
        var laa = request.getLoanApplicationAmount();
        Double score = 0D;

        Cohort cohort = cohortService.getCohortById(cohortId);

         // Calculate score for Average Daily Balance
         if(cohort.getAverageDailyBalances().size() > 0){
             score += calculateComponentScore(
                 request.getAverageDailyBalance(),
                 laa,
                 cohort.getAverageDailyBalances().get(0).getBalanceThreshold(),
                 cohort.getAverageDailyBalances().get(0).getMinWeight(),
                 weightService.getWeight(ScoringDataType.AVERAGEDAILYBALANCE).getWeight()
             );
         }


        // Calculate score for Annual Farming Income
        if(cohort.getAnnualFarmingIncomes().size() > 0){
            score += calculateComponentScore(
                request.getAnnualFarmingIncome(),
                laa,
                cohort.getAnnualFarmingIncomes().get(0).getBalanceThreshold(),
                cohort.getAnnualFarmingIncomes().get(0).getMinWeight(),
                weightService.getWeight(ScoringDataType.ANNUALFARMINGINCOME).getWeight()
            );
        }

        // Calculate score for Annual Non Farming Income
        if(cohort.getAnnualNonFarmingIncomes().size() > 0){
            score += calculateComponentScore(
                request.getAnnualNonFarmingIncome(),
                laa,
                cohort.getAnnualNonFarmingIncomes().get(0).getBalanceThreshold(),
                cohort.getAnnualNonFarmingIncomes().get(0).getMinWeight(),
                weightService.getWeight(ScoringDataType.ANNUALNONFARMINGINCOME).getWeight()
            );
        }

         // Calculate score for Annual Furtu Farming Income
         if(cohort.getAnnualFurtuFarmingIncomes().size() > 0){
            score += calculateComponentScore(
                request.getAnnualFurtuFarmingIncome(),
                laa,
                cohort.getAnnualFurtuFarmingIncomes().get(0).getBalanceThreshold(),
                cohort.getAnnualFurtuFarmingIncomes().get(0).getMinWeight(),
                weightService.getWeight(ScoringDataType.ANNUALFURTUFARMINGINCOME).getWeight()
            );
        }

        return ResponseEntity.ok(score);
    }

    
    public Double calculateComponentScore(
        Double value,
        Double laa,
        Double balanceThreshold,
        Double minWeight,
        Double weight
    ) {
        Double valueToLaa = calculatePercentage(value, laa);
        if (valueToLaa > balanceThreshold) {
            return weight;
        } else {
            Double calculatedWeight = (weight * valueToLaa) / balanceThreshold;
            return Math.max(calculatedWeight, minWeight);
        }
    }
}



