package com.dxValley.AgroFinance.ServiceImp;

import com.dxValley.AgroFinance.Models.FarmerData;
import com.dxValley.AgroFinance.Repository.FarmerDataRepository;
import com.dxValley.AgroFinance.Service.FarmerDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FarmerDataServiceImpl implements FarmerDataService {
    private final FarmerDataRepository farmerDataRepository;

    @Override
    public List<FarmerData> getAllFarmerData() {
        return farmerDataRepository.findAll();
    }

    @Override
    public FarmerData getFarmerDataById(Long id) {
        return farmerDataRepository.findById(id).orElse(null);
    }

    @Override
    public FarmerData createFarmerData(FarmerData farmerData) {
//        FarmerData farmerData1 = farmerDataRepository.findById(farmerData.getId());
        return farmerDataRepository.save(farmerData);
    }

    @Override
    public FarmerData updateFarmerData(Long id, FarmerData farmerData ){
        FarmerData farmerData1 = farmerDataRepository.findById(id).orElse(null);
        if (farmerData1 != null) {
            farmerData1.setLoanApplicationAmount(farmerData.getLoanApplicationAmount());
            farmerData1.setAverageDailyBalance(farmerData.getAverageDailyBalance());
            farmerData1.setAccountAge(farmerData.getAccountAge());
            farmerData1.setAnnualFarmingIncome(farmerData.getAnnualFarmingIncome());
            farmerData1.setAnnualNonFarmingIncome(farmerData.getAnnualNonFarmingIncome());
            farmerData1.setAnnualFurtuFarmingIncome(farmerData.getAnnualFurtuFarmingIncome());
            farmerData1.setFarmingExperience(farmerData.getFarmingExperience());

            farmerData1.setAsset(farmerData.getAsset());
            farmerData1.setIsLiterate(farmerData.getIsLiterate());
            farmerData1.setHasCreditHistory(farmerData.getHasCreditHistory());
            farmerData1.setHasDefaultHistory(farmerData.getHasDefaultHistory());
            farmerData1.setHasPaidRegularly(farmerData.getHasPaidRegularly());
            farmerData1.setHasPenalityHistory(farmerData.getHasPenalityHistory());

            return farmerDataRepository.save(farmerData1);
        }
        return null;
    }

    @Override
    public void deleteFarmerData(Long id) {
        farmerDataRepository.deleteById(id);
    }


}

