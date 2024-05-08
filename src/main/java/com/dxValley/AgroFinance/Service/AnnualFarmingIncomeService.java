package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.FarmingAndDailyBalanceDTO;
import com.dxValley.AgroFinance.Models.AnnualFarmingIncome;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.AnnualFarmingIncomeRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnualFarmingIncomeService {

    private final AnnualFarmingIncomeRepository annualFarmingIncomeRepository;
    private final CohortService cohortService;

    public AnnualFarmingIncomeService(AnnualFarmingIncomeRepository annualFarmingIncomeRepository, CohortService cohortService) {
        this.annualFarmingIncomeRepository = annualFarmingIncomeRepository;
        this.cohortService = cohortService;
    }

    public List<AnnualFarmingIncome> getDefaultAnnualFarmingIncomes() {
        return annualFarmingIncomeRepository.findByCohortIsNull();
    }

    public List<AnnualFarmingIncome> getCohortAnnualFarmingIncomes(Long cohortId) {
        return annualFarmingIncomeRepository.findByCohortId(cohortId);
    }

    public AnnualFarmingIncome createAnnualFarmingIncome(FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {

        Long cohortId = farmingAndDailyBalanceDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        AnnualFarmingIncome annualFarmingIncome = new AnnualFarmingIncome();
        annualFarmingIncome.setCohort(cohort);
        annualFarmingIncome.setBalanceThreshold(farmingAndDailyBalanceDTO.getBalanceThreshold());
        annualFarmingIncome.setDescription(farmingAndDailyBalanceDTO.getDescription());
        annualFarmingIncome.setMinWeight(farmingAndDailyBalanceDTO.getMinWeight());

        return annualFarmingIncomeRepository.save(annualFarmingIncome);
    }

    public AnnualFarmingIncome updateAnnualFarmingIncome(Long id, FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualFarmingIncome annualFarmingIncome = getAnnualFarmingIncomeById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(farmingAndDailyBalanceDTO, annualFarmingIncome, ObjectPropertyUtils.getNullPropertyNames(farmingAndDailyBalanceDTO));
        return annualFarmingIncomeRepository.save(annualFarmingIncome);
    }

    public void deleteAnnualFarmingIncome(Long id) {
        AnnualFarmingIncome annualFarmingIncome = getAnnualFarmingIncomeById(id);
        annualFarmingIncomeRepository.delete(annualFarmingIncome);
    }

    private AnnualFarmingIncome getAnnualFarmingIncomeById(Long id) {
        return annualFarmingIncomeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AnnualFarmingIncome with ID " + id + " not found"));
    }
}
