package com.solncev.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record FxRatesResponseDto (
        Boolean success,
        String timestamp,
        String base,
        Map<String, Double> rates
) {
}
