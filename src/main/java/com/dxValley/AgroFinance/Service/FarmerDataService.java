package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.Models.FarmerData;
import java.util.List;

public interface FarmerDataService {

    List<FarmerData> getAllFarmerData();
    FarmerData getFarmerDataById(Long id);
    FarmerData createFarmerData(FarmerData farmerData);
    FarmerData updateFarmerData(Long id, FarmerData farmerData);
    void deleteFarmerData(Long id);


}
