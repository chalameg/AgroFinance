package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.FarmingAndDailyBalanceDTO;
import com.dxValley.AgroFinance.Models.AnnualFurtuFarmingIncome;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.AnnualFurtuFarmingIncomeRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnualFurtuFarmingIncomeService {

    private final AnnualFurtuFarmingIncomeRepository annualFurtuFarmingIncomeRepository;
    private final CohortService cohortService;

    public AnnualFurtuFarmingIncomeService(AnnualFurtuFarmingIncomeRepository annualFurtuFarmingIncomeRepository, CohortService cohortService) {
        this.annualFurtuFarmingIncomeRepository = annualFurtuFarmingIncomeRepository;
        this.cohortService = cohortService;
    }

    public List<AnnualFurtuFarmingIncome> getDefaultAnnualFurtuFarmingIncomes() {
        return annualFurtuFarmingIncomeRepository.findByCohortIsNull();
    }

    public List<AnnualFurtuFarmingIncome> getCohortAnnualFurtuFarmingIncomes(Long cohortId) {
        return annualFurtuFarmingIncomeRepository.findByCohortId(cohortId);
    }

    public AnnualFurtuFarmingIncome createAnnualFurtuFarmingIncome(FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {

        Long cohortId = farmingAndDailyBalanceDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        AnnualFurtuFarmingIncome annualFurtuFarmingIncome = new AnnualFurtuFarmingIncome();
        annualFurtuFarmingIncome.setCohort(cohort);
        annualFurtuFarmingIncome.setBalanceThreshold(farmingAndDailyBalanceDTO.getBalanceThreshold());
        annualFurtuFarmingIncome.setDescription(farmingAndDailyBalanceDTO.getDescription());
        annualFurtuFarmingIncome.setMinWeight(farmingAndDailyBalanceDTO.getMinWeight());

        return annualFurtuFarmingIncomeRepository.save(annualFurtuFarmingIncome);
    }

    public AnnualFurtuFarmingIncome updateAnnualFurtuFarmingIncome(Long id, FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualFurtuFarmingIncome annualFurtuFarmingIncome = getAnnualFurtuFarmingIncomeById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(farmingAndDailyBalanceDTO, annualFurtuFarmingIncome, ObjectPropertyUtils.getNullPropertyNames(farmingAndDailyBalanceDTO));
        return annualFurtuFarmingIncomeRepository.save(annualFurtuFarmingIncome);
    }

    public void deleteAnnualFurtuFarmingIncome(Long id) {
        AnnualFurtuFarmingIncome annualFurtuFarmingIncome = getAnnualFurtuFarmingIncomeById(id);
        annualFurtuFarmingIncomeRepository.delete(annualFurtuFarmingIncome);
    }

    private AnnualFurtuFarmingIncome getAnnualFurtuFarmingIncomeById(Long id) {
        return annualFurtuFarmingIncomeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AnnualFurtuFarmingIncome with ID " + id + " not found"));
    }
}
