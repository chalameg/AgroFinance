package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.FarmingAndDailyBalanceDTO;
import com.dxValley.AgroFinance.Models.AverageDailyBalance;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.AverageDailyBalanceRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageDailyBalanceService {

    private final AverageDailyBalanceRepository averageDailyBalanceRepository;
    private final CohortService cohortService;

    public AverageDailyBalanceService(AverageDailyBalanceRepository averageDailyBalanceRepository, CohortService cohortService) {
        this.averageDailyBalanceRepository = averageDailyBalanceRepository;
        this.cohortService = cohortService;
    }

    public List<AverageDailyBalance> getDefaultAverageDailyBalances() {
        return averageDailyBalanceRepository.findByCohortIsNull();
    }

    public List<AverageDailyBalance> getCohortAverageDailyBalances(Long cohortId) {
        return averageDailyBalanceRepository.findByCohortId(cohortId);
    }

    public AverageDailyBalance createAverageDailyBalance(FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {

        Long cohortId = farmingAndDailyBalanceDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        AverageDailyBalance averageDailyBalance = new AverageDailyBalance();
        averageDailyBalance.setCohort(cohort);
        averageDailyBalance.setBalanceThreshold(farmingAndDailyBalanceDTO.getBalanceThreshold());
        averageDailyBalance.setDescription(farmingAndDailyBalanceDTO.getDescription());
        averageDailyBalance.setMinWeight(farmingAndDailyBalanceDTO.getMinWeight());

        return averageDailyBalanceRepository.save(averageDailyBalance);
    }

    public AverageDailyBalance updateAverageDailyBalance(Long id, FarmingAndDailyBalanceDTO farmingAndDailyBalanceDTO) {
        AverageDailyBalance averageDailyBalance = getAverageDailyBalanceById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(farmingAndDailyBalanceDTO, averageDailyBalance, ObjectPropertyUtils.getNullPropertyNames(farmingAndDailyBalanceDTO));
        return averageDailyBalanceRepository.save(averageDailyBalance);
    }

    public void deleteAverageDailyBalance(Long id) {
        AverageDailyBalance averageDailyBalance = getAverageDailyBalanceById(id);
        averageDailyBalanceRepository.delete(averageDailyBalance);
    }

    private AverageDailyBalance getAverageDailyBalanceById(Long id) {
        return averageDailyBalanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AverageDailyBalance with ID " + id + " not found"));
    }
}
