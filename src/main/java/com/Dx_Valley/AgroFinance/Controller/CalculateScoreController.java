package com.Dx_Valley.AgroFinance.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Dx_Valley.AgroFinance.DTO.AssetRequest;
import com.Dx_Valley.AgroFinance.DTO.AssetWithStatusRequest;
import com.Dx_Valley.AgroFinance.DTO.ScoreRequest;
import com.Dx_Valley.AgroFinance.DTO.ScoreRequestV2;
import com.Dx_Valley.AgroFinance.Enums.ScoringDataType;
import com.Dx_Valley.AgroFinance.Models.Asset;
import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import com.Dx_Valley.AgroFinance.Models.Education;
import com.Dx_Valley.AgroFinance.Models.FarmerAge;
import com.Dx_Valley.AgroFinance.Models.ScoringData;
import com.Dx_Valley.AgroFinance.Repository.AssetRepository;
import com.Dx_Valley.AgroFinance.Repository.AssetWithStatusRepository;
import com.Dx_Valley.AgroFinance.Repository.EducationRepository;
import com.Dx_Valley.AgroFinance.Repository.FarmerAgeRepository;
import com.Dx_Valley.AgroFinance.Service.ScoringDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/score")
public class CalculateScoreController {
    private final AssetRepository assetRepository;
    private final AssetWithStatusRepository assetWithStatusRepository;
    private final FarmerAgeRepository farmerAgeRepository;
    private final EducationRepository educationRepository;
    private final ScoringDataService scoringDataService;

    // note create different table for registaring weight for each assets

    @PostMapping("/calculate")
    private ResponseEntity<Double> calculateScore(@RequestBody ScoreRequest request) {
        Double totalScore = 0D;

        for (AssetRequest assetRequest : request.getAssets()) {
            Asset asset = assetRepository.findByAssetName(assetRequest.getAssetName());

            if (asset != null) {
                Double assetValue = assetRequest.getAssetValue();
                Double value = 0D;

                if (asset.getIsValueIncreasing()) {
                    value = Math.min(Math.ceil(assetValue / asset.getAssetIncrement()), asset.getAssetStandard());
                } else if (assetRequest.getAssetName().equals("FamilySize") && assetValue == 0) {
                    value = 0D;
                } else {
                    value = Math.max(asset.getAssetStartValue() - Math.floor(assetValue / asset.getAssetIncrement()),
                            asset.getAssetEndValue());
                    System.out.println("$$$$$$$$$$$$$$$$$ "+ assetRequest.getAssetName()+ value);
                }

                // handle case when distance is zero
                Double weight = asset.getAssetWeight();
                Double normalizedValue = value / asset.getAssetStandard();

                System.out.println("###########################, "+ assetRequest.getAssetName()+ " " +normalizedValue * weight);

                totalScore += normalizedValue * weight;
            }
        }

        // handle case for assets with status
        for (AssetWithStatusRequest assetWithStatausRequest : request.getAssetsWithStatus()) {
            if (assetWithStatausRequest.getAssetValue().equals("yes")) {
                AssetWithStatus assetWithStatus = assetWithStatusRepository
                        .findByStatusName(assetWithStatausRequest.getAssetName());

                if (assetWithStatus != null) {
                    totalScore += assetWithStatus.getStatusWeight();
                    System.out.println("###########################, "+ assetWithStatausRequest.getAssetName()+ " " +assetWithStatus.getStatusWeight());
                }
            }
        }

        // handle age case
        Double age = request.getAge();
        Double val;
        FarmerAge farmerAge = farmerAgeRepository
                .findByAgeIntervalStartLessThanEqualAndAgeIntervalEndGreaterThanEqual(age, age);
        System.out.println("############"+farmerAge);
        if (farmerAge != null) {
            val = farmerAge.getAgeValue();
            Double weight = farmerAge.getWeight();
            Double normalizedValue = val / farmerAge.getAgeStandard();
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&: farmer age"+normalizedValue * weight);
            totalScore += normalizedValue * weight;
        } else {
            val = 1D;
            FarmerAge farmerAge2 = farmerAgeRepository.findById(1L).orElseThrow();
            Double weight = farmerAge2.getWeight();
            Double normalizedValue = val / farmerAge2.getAgeStandard();

            totalScore += normalizedValue * weight;
        }


         // Handle education scoring
        Education education = educationRepository.findByLevel(request.getEducationLevel());
        if (education != null) {
            Double weight = education.getWeight();
            Double normalizedValue = education.getScoreValue() / education.getStandard();

            totalScore += normalizedValue * weight;
        }

        return ResponseEntity.ok(totalScore);
    }

    @PostMapping("/calculatev2")
    public ResponseEntity<Double> calculateScorev2(@RequestBody ScoreRequestV2 request) {
        Double totalScore = 0D;
        Double laa = request.getLoanApplicationAmount();

        totalScore += calculateScoreForType(ScoringDataType.AVERAGEDAILYBALANCE, request.getAverageDailyBalance(), laa);
        totalScore += calculateScoreForType(ScoringDataType.ANNUALFARMINCOME, request.getAnnualFarmIncome(), laa);
        totalScore += calculateScoreForType(ScoringDataType.ANNUALNONFARMINCOME, request.getAnnualNonFarmIncome(), laa);
        // no need of laa for farming exprience and account age
        totalScore += calculateScoreForType(ScoringDataType.FARMINGEXPERIENCE, request.getFarmingExperience(), laa); 
        totalScore += calculateScoreForType(ScoringDataType.ACCOUNTAGE, request.getAccountAge(), laa);

        totalScore += calculateScoreForType(ScoringDataType.ANNUALFURTUFARMINCOME, request.getAnnualFurtuFarmIncome(), laa);
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

        return ResponseEntity.ok(totalScore);
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
    
}
