package com.Dx_Valley.AgroFinance.Service;

import com.Dx_Valley.AgroFinance.Models.FarmerAge;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FarmerAgeService {

    List<FarmerAge> fetchFarmerAge();
    
    FarmerAge editFarmerAge (FarmerAge farmerAge);

    ResponseEntity<?> registerFarmerAge(FarmerAge farmerAge);
}



