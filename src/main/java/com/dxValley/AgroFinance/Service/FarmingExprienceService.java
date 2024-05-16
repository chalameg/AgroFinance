package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.AccountDurationDTO;
import com.dxValley.AgroFinance.Models.FarmingExprience;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.FarmingExprienceRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmingExprienceService {

    private final FarmingExprienceRepository farmingExprienceRepository;
    private final CohortService cohortService;

    public FarmingExprienceService(FarmingExprienceRepository farmingExprienceRepository, CohortService cohortService) {
        this.farmingExprienceRepository = farmingExprienceRepository;
        this.cohortService = cohortService;
    }

    public List<FarmingExprience> getDefaultFarmingExpriences() {
        return farmingExprienceRepository.findByCohortIsNull();
    }

    public List<FarmingExprience> getCohortFarmingExpriences(Long cohortId) {
        return farmingExprienceRepository.findByCohortId(cohortId);
    }

    public FarmingExprience createFarmingExprience(AccountDurationDTO accountDurationDTO) {

        Long cohortId = accountDurationDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        FarmingExprience farmingExprience = new FarmingExprience();
        farmingExprience.setCohort(cohort);
        farmingExprience.setMaxMonth(accountDurationDTO.getMaxMonth());
        farmingExprience.setMinMonth(accountDurationDTO.getMinMonth());
        farmingExprience.setDescription(accountDurationDTO.getDescription());
        farmingExprience.setMinWeight(accountDurationDTO.getMinWeight());

        return farmingExprienceRepository.save(farmingExprience);
    }

    public FarmingExprience updateFarmingExprience(Long id, AccountDurationDTO accountDurationDTO) {
        FarmingExprience farmingExprience = getFarmingExprienceById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(accountDurationDTO, farmingExprience, ObjectPropertyUtils.getNullPropertyNames(accountDurationDTO));
        return farmingExprienceRepository.save(farmingExprience);
    }

    public void deleteFarmingExprience(Long id) {
        FarmingExprience farmingExprience = getFarmingExprienceById(id);
        farmingExprienceRepository.delete(farmingExprience);
    }

    private FarmingExprience getFarmingExprienceById(Long id) {
        return farmingExprienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farming Exprience with ID " + id + " not found"));
    }
}
