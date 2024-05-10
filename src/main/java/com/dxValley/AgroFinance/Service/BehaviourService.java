package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.BehaviourEducationDTO;
import com.dxValley.AgroFinance.Models.Behaviour;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.BehaviourRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BehaviourService {

    private final BehaviourRepository behaviourRepository;
    private final CohortService cohortService;

    public BehaviourService(BehaviourRepository behaviourRepository, CohortService cohortService) {
        this.behaviourRepository = behaviourRepository;
        this.cohortService = cohortService;
    }

    public List<Behaviour> getDefaultBehaviours() {
        return behaviourRepository.findByCohortIsNull();
    }

    public List<Behaviour> getCohortBehaviours(Long cohortId) {
        return behaviourRepository.findByCohortId(cohortId);
    }

    public Behaviour createBehaviour(BehaviourEducationDTO behaviourEducationDTO) {

        Long cohortId = behaviourEducationDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        Behaviour behaviour = new Behaviour();
        behaviour.setCohort(cohort);
        behaviour.setName(behaviourEducationDTO.getName());
        behaviour.setDescription(behaviourEducationDTO.getDescription());

        return behaviourRepository.save(behaviour);
    }

    public Behaviour updateBehaviour(Long id, BehaviourEducationDTO behaviourEducationDTO) {
        Behaviour behaviour = getBehaviourById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(behaviourEducationDTO, behaviour, ObjectPropertyUtils.getNullPropertyNames(behaviourEducationDTO));
        return behaviourRepository.save(behaviour);
    }

    public void deleteBehaviour(Long id) {
        Behaviour behaviour = getBehaviourById(id);
        behaviourRepository.delete(behaviour);
    }

    private Behaviour getBehaviourById(Long id) {
        return behaviourRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Behaviour with ID " + id + " not found"));
    }
}
