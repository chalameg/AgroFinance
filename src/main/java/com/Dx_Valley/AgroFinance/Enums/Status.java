package com.Dx_Valley.AgroFinance.Enums;
import java.util.Arrays;

public enum Status {
    YES,
    NO;
    public static Status lookup(String  status) {
        return Arrays.stream(Status.values())
                .filter(e -> e.name().equalsIgnoreCase(status)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Status."));
    }
}