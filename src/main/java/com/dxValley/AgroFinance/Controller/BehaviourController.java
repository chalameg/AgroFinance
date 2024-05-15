package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.BehaviourEducationDTO;
import com.dxValley.AgroFinance.Models.Behaviour;
import com.dxValley.AgroFinance.Service.BehaviourService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/api/behaviours")
@Tag(name = "Farmer Behaviour APIs.")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600L)

public class BehaviourController {

    private final BehaviourService behaviourService;

    public BehaviourController(BehaviourService behaviourService) {
        this.behaviourService = behaviourService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<Behaviour>> getDefaultBehaviours() {
        List<Behaviour> Behaviours = behaviourService.getDefaultBehaviours();
        return ResponseEntity.ok(Behaviours);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<Behaviour>> getCohortBehaviours(@PathVariable Long cohortId) {
        List<Behaviour> Behaviours = behaviourService.getCohortBehaviours(cohortId);
        return ResponseEntity.ok(Behaviours);
    }

    @PostMapping
    public ResponseEntity<Behaviour> createBehaviour(@RequestBody @Valid BehaviourEducationDTO behaviourEducationDTO) {
        Behaviour createdBehaviour = behaviourService.createBehaviour(behaviourEducationDTO);
        return ResponseEntity.ok(createdBehaviour);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Behaviour> updateBehaviour(@PathVariable Long id, @RequestBody BehaviourEducationDTO behaviourEducationDTO) {
        Behaviour Behaviour = behaviourService.updateBehaviour(id, behaviourEducationDTO);
        return ResponseEntity.ok(Behaviour);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteBehaviour(@PathVariable Long id) {
        behaviourService.deleteBehaviour(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
