package com.dxValley.AgroFinance.Service;


import org.springframework.stereotype.Service;

import com.dxValley.AgroFinance.DTO.CohortDTO;
import com.dxValley.AgroFinance.DTO.CohortListResponse;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.CohortRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class CohortService {

    private final CohortRepository cohortRepository;

    public CohortService(CohortRepository cohortRepository) {
        this.cohortRepository = cohortRepository;
    }

    public List<CohortListResponse> getAllCohorts() {
        List<Cohort> cohorts = cohortRepository.findAllByOrderByName(); 

        return cohorts.stream()
                .map(cohort -> {
                    CohortListResponse cohortResponse = new CohortListResponse();
                    cohortResponse.setId(cohort.getId());
                    cohortResponse.setName(cohort.getName());
                    cohortResponse.setDescription(cohort.getDescription());
                    return cohortResponse;
                })
                .toList();
    }

    public Cohort getCohortById(Long id) {
        return cohortRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cohort with ID " + id + " not found"));
    }

    public Cohort createCohort(CohortDTO cohortDTO) {
        Cohort cohort = new Cohort();

        cohort.setName(cohortDTO.getName());
        cohort.setDescription(cohortDTO.getDescription());

        return cohortRepository.save(cohort);
    }

    public Cohort updateCohort(Long id, CohortDTO cohortDTO) {
        Cohort cohort = getCohortById(id);

        if (cohortDTO.getName() != null)
            cohort.setName(cohortDTO.getName());

        if (cohortDTO.getDescription() != null)
            cohort.setDescription(cohortDTO.getDescription());

        return cohortRepository.save(cohort);
    }

    public void deleteCohort(Long id) {
        Cohort cohort = getCohortById(id);
        cohortRepository.delete(cohort);
    }
}
