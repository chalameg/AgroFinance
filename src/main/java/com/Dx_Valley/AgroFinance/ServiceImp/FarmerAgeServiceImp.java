package com.Dx_Valley.AgroFinance.ServiceImp;

import com.Dx_Valley.AgroFinance.DTO.ResponseMessage;
import com.Dx_Valley.AgroFinance.Models.FarmerAge;
import com.Dx_Valley.AgroFinance.Repository.FarmerAgeRepository;
import com.Dx_Valley.AgroFinance.Service.FarmerAgeService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FarmerAgeServiceImp implements FarmerAgeService {

    private final FarmerAgeRepository farmerAgeRepository;

    @Override
    public ResponseEntity<?> registerFarmerAge(FarmerAge farmerAge) {

        farmerAgeRepository.save(farmerAge);
        ResponseMessage responseMessage = new ResponseMessage("success", "FarmerAge created successfully");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    @Override
    public FarmerAge editFarmerAge(FarmerAge farmerAge) {
        return farmerAgeRepository.save(farmerAge);
    }

    @Override
    public List<FarmerAge> fetchFarmerAge() {
        List<FarmerAge> farmerAge = new ArrayList<>();
        farmerAge.addAll(farmerAgeRepository.findAll());
        return farmerAge;
    }
}
