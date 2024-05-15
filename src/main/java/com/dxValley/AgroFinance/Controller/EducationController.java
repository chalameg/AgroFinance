package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.BehaviourEducationDTO;
import com.dxValley.AgroFinance.Models.Education;
import com.dxValley.AgroFinance.Service.EducationService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/api/educations")
@Tag(name = "Farmer Education APIs.")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600L)

public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<Education>> getDefaultEducations() {
        List<Education> Educations = educationService.getDefaultEducations();
        return ResponseEntity.ok(Educations);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<Education>> getCohortEducations(@PathVariable Long cohortId) {
        List<Education> Educations = educationService.getCohortEducations(cohortId);
        return ResponseEntity.ok(Educations);
    }

    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody @Valid BehaviourEducationDTO behaviourEducationDTO) {
        Education createdEducation = educationService.createEducation(behaviourEducationDTO);
        return ResponseEntity.ok(createdEducation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @RequestBody BehaviourEducationDTO behaviourEducationDTO) {
        Education Education = educationService.updateEducation(id, behaviourEducationDTO);
        return ResponseEntity.ok(Education);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
