package com.dxValley.AgroFinance.Enums;
import java.util.Arrays;

public enum ScoringDataType {
    AVERAGEDAILYBALANCE,
    ANNUALFARMINCOME,
    ANNUALNONFARMINCOME,
    ANNUALFURTUFARMINCOME,
    ACCOUNTAGE,
    FARMINGEXPERIENCE,
    ASSET,
    ILLITERATE,
    LITERATE,
    GOODBEHAVIOUR,
    MODERATEBEHAVIOUR,
    BADBEHAVIOUR;
    public static ScoringDataType lookup(String  scoringDataType) {
        return Arrays.stream(ScoringDataType.values())
                .filter(e -> e.name().equalsIgnoreCase(scoringDataType)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid scoringDataType."));
    }
}