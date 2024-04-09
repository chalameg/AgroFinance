package com.Dx_Valley.AgroFinance.Repository;
import com.Dx_Valley.AgroFinance.Models.AssetWithStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetWithStatusRepository extends JpaRepository<AssetWithStatus, Long> {
    AssetWithStatus findByStatusName(String statusName);
}


