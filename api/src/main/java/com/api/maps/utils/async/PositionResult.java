package com.api.maps.utils.async;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionResult {
    private final double amountAvailable;
    private final double valueMarketplace;
    private final double valueGain;
    private final double valueYield;
    private final String nameActive;
}
