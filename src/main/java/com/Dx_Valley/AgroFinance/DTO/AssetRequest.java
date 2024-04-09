package com.Dx_Valley.AgroFinance.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetRequest {
    private String assetName;
    private Double assetValue;
}
