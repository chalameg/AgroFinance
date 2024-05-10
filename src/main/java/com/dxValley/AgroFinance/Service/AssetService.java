package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.AssetDTO;
import com.dxValley.AgroFinance.Models.Asset;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.AssetRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final CohortService cohortService;

    public AssetService(AssetRepository assetRepository, CohortService cohortService) {
        this.assetRepository = assetRepository;
        this.cohortService = cohortService;
    }

    public List<Asset> getDefaultAssets() {
        return assetRepository.findByCohortIsNull();
    }

    public List<Asset> getCohortAssets(Long cohortId) {
        return assetRepository.findByCohortId(cohortId);
    }

    public Asset createAsset(AssetDTO assetDTO) {

        Long cohortId = assetDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        Asset asset = new Asset();
        asset.setCohort(cohort);
        asset.setBalanceThreshold(assetDTO.getBalanceThreshold());
        asset.setDescription(assetDTO.getDescription());
        asset.setMinWeight(assetDTO.getMinWeight());

        return assetRepository.save(asset);
    }

    public Asset updateAsset(Long id, AssetDTO assetDTO) {
        Asset asset = getAssetById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(assetDTO, asset, ObjectPropertyUtils.getNullPropertyNames(assetDTO));
        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        Asset asset = getAssetById(id);
        assetRepository.delete(asset);
    }

    private Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("asset with ID " + id + " not found"));
    }
}
