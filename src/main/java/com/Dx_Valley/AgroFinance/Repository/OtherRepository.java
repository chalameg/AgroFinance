package com.Dx_Valley.AgroFinance.Repository;
import com.Dx_Valley.AgroFinance.Models.OtherAsset;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OtherRepository extends  JpaRepository<OtherAsset, Long> {
    OtherAsset findByName(String otherName);
}


