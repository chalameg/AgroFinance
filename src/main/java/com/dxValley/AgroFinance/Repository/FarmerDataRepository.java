package com.dxValley.AgroFinance.Repository;

import com.dxValley.AgroFinance.Models.FarmerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerDataRepository extends JpaRepository<FarmerData, Long> {
    // FarmerData findById(Long Id)
    //custom query method

}
