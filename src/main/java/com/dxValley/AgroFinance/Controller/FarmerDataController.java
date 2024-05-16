package com.dxValley.AgroFinance.Controller;

import com.dxValley.AgroFinance.Models.FarmerData;
import com.dxValley.AgroFinance.Repository.FarmerDataRepository;
import com.dxValley.AgroFinance.Service.FarmerDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/Farmer-data")
@Tag(name = "Farmer Data APIs.")
public class FarmerDataController {
    @Autowired
    private FarmerDataRepository farmerDataRepository;

    @Autowired
    private FarmerDataService farmerDataService;

    @PostMapping
    public ResponseEntity<FarmerData> createFarmerData(@RequestBody FarmerData farmerData){
        FarmerData farmerData1 = farmerDataService.createFarmerData(farmerData);
        return new ResponseEntity<>(farmerData1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FarmerData>> getAllFarmerData() {
        List<FarmerData> farmerDataList = farmerDataService.getAllFarmerData();
        return  new ResponseEntity<>(farmerDataList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmerData> getFarmerDataById(@PathVariable Long id) {
        FarmerData farmerData1 = farmerDataService.getFarmerDataById(id);
        if (farmerData1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(farmerData1, HttpStatus.OK);

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<FarmerData> updateFarmerData(@PathVariable Long id, @RequestBody FarmerData farmerData){
        FarmerData farmerData1 = farmerDataService.updateFarmerData(id, farmerData);
        if(farmerData1==null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return  new ResponseEntity<>(farmerData1, HttpStatus.OK);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarmerData(@PathVariable Long id){
        farmerDataService.deleteFarmerData(id);
   return  ResponseEntity.ok("Successfully deleted");


    }

}
