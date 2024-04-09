package com.Dx_Valley.AgroFinance.Controller;

import com.Dx_Valley.AgroFinance.DTO.EducationRequest;
import com.Dx_Valley.AgroFinance.Models.Education;
import com.Dx_Valley.AgroFinance.Repository.EducationRepository;
import com.Dx_Valley.AgroFinance.Service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;
    private final EducationRepository educationRepository;

    @GetMapping
    public ResponseEntity<?> getEducations(){
        return ResponseEntity.ok(educationRepository.findAll());
    }
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @PostMapping("/register")
    public ResponseEntity<Education> registerEducation(@RequestBody EducationRequest request) {
        Education newEducation = educationService.registerEducation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEducation);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Education> editEducation(@PathVariable Long id, @RequestBody EducationRequest request) {
        Education updatedEducation = educationService.editEducation(id, request);
        if (updatedEducation != null) {
            return ResponseEntity.ok(updatedEducation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
        Education education = educationService.getEducationById(id);
        if (education != null) {
            return ResponseEntity.ok(education);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
