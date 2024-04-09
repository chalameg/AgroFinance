package com.Dx_Valley.AgroFinance.Controller;

import com.Dx_Valley.AgroFinance.DTO.createUserResponse;
import com.Dx_Valley.AgroFinance.Models.FarmerAge;
import com.Dx_Valley.AgroFinance.Repository.FarmerAgeRepository;
import com.Dx_Valley.AgroFinance.Service.FarmerAgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farmerAge")
public class FarmerAgeController {

    private final FarmerAgeService farmerAgeService;
    private final FarmerAgeRepository farmerAgeRepository;

    @PostMapping
    public ResponseEntity<?> createFarmerAge(@RequestBody FarmerAge FarmerAge) {
        return farmerAgeService.registerFarmerAge(FarmerAge);

    }

    @GetMapping
    private ResponseEntity<?> fetchFarmerAges() {
        List<FarmerAge> FarmerAge = farmerAgeService.fetchFarmerAge();
        return new ResponseEntity<>(FarmerAge, HttpStatus.OK);
    }

    @GetMapping("/{farmerAgeId}")
    public ResponseEntity<?> getByFarmerAgeId(@PathVariable Long farmerAgeId) {
        var FarmerAge = farmerAgeRepository.findById(farmerAgeId);
        if (FarmerAge == null) {
            createUserResponse response = new createUserResponse("error", "Cannot find this FarmerAge!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(FarmerAge, HttpStatus.OK);
    }

    @PatchMapping("/edit/{farmerAgeId}")
    FarmerAge editFarmerAge(@RequestBody FarmerAge FarmerAge, @PathVariable Long farmerAgeId) {
        FarmerAge farmerAge1 = farmerAgeRepository.findById(farmerAgeId).orElseThrow();
       
        if(FarmerAge.getAgeIntervalStart() != null){
            farmerAge1.setAgeIntervalStart(FarmerAge.getAgeIntervalStart());
        }
        if(FarmerAge.getAgeIntervalEnd() != null){
            farmerAge1.setAgeIntervalEnd(FarmerAge.getAgeIntervalEnd());
        }
        if(FarmerAge.getAgeStandard() != null){
            farmerAge1.setAgeStandard(FarmerAge.getAgeStandard());
        }
        if(FarmerAge.getWeight() != null){
            farmerAge1.setWeight(FarmerAge.getWeight());
        }
        if(FarmerAge.getAgeValue() != null){
            farmerAge1.setAgeValue(FarmerAge.getAgeValue());
        }
       
        return farmerAgeService.editFarmerAge(farmerAge1);
    }

    @DeleteMapping("/delete/{farmerAgeId}")
    void deleteOthers(@PathVariable Long farmerAgeId) {
        farmerAgeRepository.deleteById(farmerAgeId);
    }

}
