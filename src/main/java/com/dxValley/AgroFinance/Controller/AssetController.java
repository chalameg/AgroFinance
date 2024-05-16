package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.AssetDTO;
import com.dxValley.AgroFinance.Models.Asset;
import com.dxValley.AgroFinance.Service.AssetService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/api/assets")
@Tag(name = "Farmer Asset APIs.")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600L)

public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<Asset>> getDefaultAssets() {
        List<Asset> Assets = assetService.getDefaultAssets();
        return ResponseEntity.ok(Assets);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<Asset>> getCohortAssets(@PathVariable Long cohortId) {
        List<Asset> Assets = assetService.getCohortAssets(cohortId);
        return ResponseEntity.ok(Assets);
    }

    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody @Valid AssetDTO assetDTO) {
        Asset createdAsset = assetService.createAsset(assetDTO);
        return ResponseEntity.ok(createdAsset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody AssetDTO assetDTO) {
        Asset Asset = assetService.updateAsset(id, assetDTO);
        return ResponseEntity.ok(Asset);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}
