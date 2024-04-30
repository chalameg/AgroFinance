package com.dxValley.AgroFinance.Repository;

import com.dxValley.AgroFinance.Models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long>{
    
}
