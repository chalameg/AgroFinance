package com.Dx_Valley.AgroFinance.Service;

import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssetWithStatusService {
    List<AssetWithStatus> fetchAssetWithStatus();
    AssetWithStatus editAsset (AssetWithStatus asset);
    ResponseEntity<?> registerAssetWithStatus(AssetWithStatus status);
}



