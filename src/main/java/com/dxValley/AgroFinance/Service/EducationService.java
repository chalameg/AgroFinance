package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.BehaviourEducationDTO;
import com.dxValley.AgroFinance.Models.Education;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.EducationRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final CohortService cohortService;

    public EducationService(EducationRepository educationRepository, CohortService cohortService) {
        this.educationRepository = educationRepository;
        this.cohortService = cohortService;
    }

    public List<Education> getDefaultEducations() {
        return educationRepository.findByCohortIsNull();
    }

    public List<Education> getCohortEducations(Long cohortId) {
        return educationRepository.findByCohortId(cohortId);
    }

    public Education createEducation(BehaviourEducationDTO behaviourEducationDTO) {

        Long cohortId = behaviourEducationDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        Education education = new Education();
        education.setCohort(cohort);
        education.setName(behaviourEducationDTO.getName());
        education.setDescription(behaviourEducationDTO.getDescription());

        return educationRepository.save(education);
    }

    public Education updateEducation(Long id, BehaviourEducationDTO behaviourEducationDTO) {
        Education education = getEducationById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(behaviourEducationDTO, education, ObjectPropertyUtils.getNullPropertyNames(behaviourEducationDTO));
        return educationRepository.save(education);
    }

    public void deleteEducation(Long id) {
        Education education = getEducationById(id);
        educationRepository.delete(education);
    }

    private Education getEducationById(Long id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Education with ID " + id + " not found"));
    }
}
