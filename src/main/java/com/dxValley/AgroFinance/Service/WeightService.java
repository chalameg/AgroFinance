package com.dxValley.AgroFinance.Service;
import com.dxValley.AgroFinance.DTO.WeightDTO;
import com.dxValley.AgroFinance.Enums.ScoringDataType;
import com.dxValley.AgroFinance.Models.Weight;
import com.dxValley.AgroFinance.Repository.WeightRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceAlreadyExistsException;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class WeightService {

    private final WeightRepository weightRepository;

    public WeightService(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    public Weight getWeight(ScoringDataType scoringDataType) {
        return weightRepository.findByScoringDataType(scoringDataType);
    }

    public List<Weight> getWeights() {
        return weightRepository.findAll();
    }
    
    public Weight createWeight(WeightDTO weightReq) {
        Weight existingWeight = getWeight(weightReq.getScoringDataType());
        if(existingWeight!=null){
            throw new ResourceAlreadyExistsException("Weight with similar data type already exists");
        }
        Weight weight = new Weight();
        weight.setScoringDataType(weightReq.getScoringDataType());
        weight.setWeight(weightReq.getWeight());

        return weightRepository.save(weight);
    }

    public Weight updateWeight(Long id, WeightDTO WeightReq) {
        Weight weight = getWeightById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(WeightReq, weight, ObjectPropertyUtils.getNullPropertyNames(weight));
        return weightRepository.save(weight);
    }

    public void deleteWeight(Long id) {
        Weight Weight = getWeightById(id);
        weightRepository.delete(Weight);
    }

    private Weight getWeightById(Long id) {
        return weightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Weight with ID " + id + " not found"));
    }
}
