package com.plenigo.task.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "plenigo")
@Data
public class AppConfig {

    private String url;
    private String apiKeyValue;
    private String apiKeyKey;

}
