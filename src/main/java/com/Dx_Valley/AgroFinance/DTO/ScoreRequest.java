package com.Dx_Valley.AgroFinance.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRequest {
    private List<AssetRequest> assets;
    private List<AssetWithStatusRequest> assetsWithStatus;
    private Double age;
    private String educationLevel;
}
