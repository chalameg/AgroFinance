package com.Dx_Valley.AgroFinance.Repository;

import com.Dx_Valley.AgroFinance.Models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long>{
    Asset findByAssetName(String assetName);
}
