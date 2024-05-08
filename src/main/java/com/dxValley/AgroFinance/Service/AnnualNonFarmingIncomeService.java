package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.FarmingAndDailyBalanceDTO;
import com.dxValley.AgroFinance.Models.AnnualNonFarmingIncome;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.AnnualNonFarmingIncomeRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnualNonFarmingIncomeService {

    private final AnnualNonFarmingIncomeRepository annualNonFarmingIncomeRepository;
    private final CohortService cohortService;

    public AnnualNonFarmingIncomeService(AnnualNonFarmingIncomeRepository annualNonFarmingIncomeRepository, CohortService cohortService) {
        this.annualNonFarmingIncomeRepository = annualNonFarmingIncomeRepository;
        this.cohortService = cohortService;
    }

    public List<AnnualNonFarmingIncome> getDefaultAnnualNonFarmingIncomes() {
        return annualNonFarmingIncomeRepository.findByCohortIsNull();
    }

    public List<AnnualNonFarmingIncome> getCohortAnnualNonFarmingIncomes(Long cohortId) {
        return annualNonFarmingIncomeRepository.findByCohortId(cohortId);
    }

    public AnnualNonFarmingIncome createAnnualNonFarmingIncome(FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {

        Long cohortId = farmingAndDailyBalanceDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        AnnualNonFarmingIncome annualNonFarmingIncome = new AnnualNonFarmingIncome();
        annualNonFarmingIncome.setCohort(cohort);
        annualNonFarmingIncome.setBalanceThreshold(farmingAndDailyBalanceDTO.getBalanceThreshold());
        annualNonFarmingIncome.setDescription(farmingAndDailyBalanceDTO.getDescription());
        annualNonFarmingIncome.setMinWeight(farmingAndDailyBalanceDTO.getMinWeight());

        return annualNonFarmingIncomeRepository.save(annualNonFarmingIncome);
    }

    public AnnualNonFarmingIncome updateAnnualNonFarmingIncome(Long id, FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AnnualNonFarmingIncome annualNonFarmingIncome = getAnnualNonFarmingIncomeById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(farmingAndDailyBalanceDTO, annualNonFarmingIncome, ObjectPropertyUtils.getNullPropertyNames(farmingAndDailyBalanceDTO));
        return annualNonFarmingIncomeRepository.save(annualNonFarmingIncome);
    }

    public void deleteAnnualNonFarmingIncome(Long id) {
        AnnualNonFarmingIncome annualNonFarmingIncome = getAnnualNonFarmingIncomeById(id);
        annualNonFarmingIncomeRepository.delete(annualNonFarmingIncome);
    }

    private AnnualNonFarmingIncome getAnnualNonFarmingIncomeById(Long id) {
        return annualNonFarmingIncomeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AnnualNonFarmingIncome with ID " + id + " not found"));
    }
}
