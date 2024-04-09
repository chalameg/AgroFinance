package com.Dx_Valley.AgroFinance.ServiceImp;

import com.Dx_Valley.AgroFinance.DTO.EducationRequest;
import com.Dx_Valley.AgroFinance.Models.Education;
import com.Dx_Valley.AgroFinance.Repository.EducationRepository;
import com.Dx_Valley.AgroFinance.Service.EducationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationServiceImp implements EducationService {
    private final EducationRepository educationRepository;

    @Override
    public Education registerEducation(EducationRequest request) {
        Education education = new Education();
        education.setLevel(request.getLevel());
        education.setScoreValue(request.getScoreValue());
        education.setStandard(request.getStandard());
        education.setWeight(request.getWeight());
        return educationRepository.save(education);
    }

    @Override
    public Education editEducation(Long id, EducationRequest request) {
        Education education = educationRepository.findById(id).orElse(null);
        if (education != null) {
            education.setLevel(request.getLevel());
            education.setScoreValue(request.getScoreValue());
            education.setStandard(request.getStandard());
            education.setWeight(request.getWeight());
            return educationRepository.save(education);
        }
        return null;
    }

    @Override
    public Education getEducationById(Long id) {
        return educationRepository.findById(id).orElse(null);
    }
}
