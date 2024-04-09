package com.Dx_Valley.AgroFinance.Controller;

import com.Dx_Valley.AgroFinance.DTO.createUserResponse;
import com.Dx_Valley.AgroFinance.Models.Asset;
import com.Dx_Valley.AgroFinance.Repository.AssetRepository;
import com.Dx_Valley.AgroFinance.Service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;
    private final AssetRepository assetRepository;

    @PostMapping
    public ResponseEntity<?> createAsset(@RequestBody Asset asset) {
        return assetService.registerAsset(asset);

    }

    @GetMapping
    private ResponseEntity<?> fetchOthers() {
        List<Asset> asset = assetService.fetchAsset();
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<?> getByAssetId(@PathVariable Long assetId) {
        var asset = assetRepository.findById(assetId);
        if (asset == null) {
            createUserResponse response = new createUserResponse("error", "Cannot find this asset!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @PatchMapping("/edit/{assetId}")
    Asset editAsset(@RequestBody Asset asset, @PathVariable Long assetId) {
        Asset asset1 = assetRepository.findById(assetId).orElseThrow();
        if(asset.getAssetName() != null){
            asset1.setAssetName(asset.getAssetName());
        }
        if(asset.getAssetDescription() != null){
            asset1.setAssetDescription(asset.getAssetDescription());
        }
        if(asset.getAssetWeight() != null){
            asset1.setAssetWeight(asset.getAssetWeight());
        }
        if(asset.getAssetIncrement() != null){
            asset1.setAssetIncrement(asset.getAssetIncrement());
        }
        if(asset.getAssetIntervalStart() != null){
            asset1.setAssetIntervalStart(asset.getAssetIntervalStart());
        }
        if(asset.getAssetIntervalEnd() != null){
            asset1.setAssetIntervalEnd(asset.getAssetIntervalEnd());
        }
        if(asset.getAssetStartValue() != null){
            asset1.setAssetStartValue(asset.getAssetStartValue());
        }
        if(asset.getAssetEndValue() != null){
            asset1.setAssetEndValue(asset.getAssetEndValue());
        }
        return assetService.editAsset(asset1);
    }

    @DeleteMapping("/delete/{otherId}")
    void deleteOthers(@PathVariable Long assetId) {
        this.assetRepository.deleteById(assetId);
    }

}
