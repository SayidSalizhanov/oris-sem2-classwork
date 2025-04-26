package com.solncev.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "fxrates.api")
public class FxRatesApiProperties {
    private String apiKey;
    private String baseUrl;
}