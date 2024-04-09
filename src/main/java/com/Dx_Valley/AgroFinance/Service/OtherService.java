package com.Dx_Valley.AgroFinance.Service;
import com.Dx_Valley.AgroFinance.Models.OtherAsset;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OtherService {
    List<OtherAsset> fetchOthers();
    OtherAsset editOthers (OtherAsset others);

    ResponseEntity<?> registerOthers(OtherAsset others);

}



