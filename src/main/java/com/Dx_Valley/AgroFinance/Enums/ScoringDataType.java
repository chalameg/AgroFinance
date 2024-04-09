package com.Dx_Valley.AgroFinance.Enums;
import java.util.Arrays;

public enum ScoringDataType {
    AVERAGEDAILYBALANCE,
    ANNUALFARMINCOME,
    ANNUALNONFARMINCOME,
    ANNUALFURTUFARMINCOME,
    ACCOUNTAGE,
    FARMINGEXPERIENCE,
    ASSET;
    public static ScoringDataType lookup(String  scoringDataType) {
        return Arrays.stream(ScoringDataType.values())
                .filter(e -> e.name().equalsIgnoreCase(scoringDataType)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid scoringDataType."));
    }
}