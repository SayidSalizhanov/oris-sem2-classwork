package com.solncev.service;

import com.solncev.properties.FxRatesApiProperties;
import com.solncev.dto.FxRatesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FxRatesApiService {
    private final FxRatesApiProperties config;
    private final RestTemplate restTemplate;

    public Map<String, Double> getLatestRates(String base) {
        String url = UriComponentsBuilder
                .fromHttpUrl(config.getBaseUrl())
                .queryParam("apikey", config.getApiKey())
                .queryParam("base", base)
                .build()
                .toUriString();

        FxRatesResponseDto response = restTemplate.getForObject(url, FxRatesResponseDto.class);
        return response != null ? response.rates() : null;
    }

    public Map<String, Double> getLatestRates(String base, String... symbols) {
        String url = UriComponentsBuilder
                .fromHttpUrl(config.getBaseUrl())
                .queryParam("apikey", config.getApiKey())
                .queryParam("base", base)
                .queryParam("symbols", String.join(",", symbols))
                .build()
                .toUriString();

        FxRatesResponseDto response = restTemplate.getForObject(url, FxRatesResponseDto.class);
        return response != null ? response.rates() : null;
    }
} 