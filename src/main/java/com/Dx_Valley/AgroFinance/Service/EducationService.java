package com.Dx_Valley.AgroFinance.Service;

import com.Dx_Valley.AgroFinance.DTO.EducationRequest;
import com.Dx_Valley.AgroFinance.Models.Education;

public interface EducationService {
    Education registerEducation(EducationRequest request);
    Education editEducation(Long id, EducationRequest request);
    Education getEducationById(Long id);
}
