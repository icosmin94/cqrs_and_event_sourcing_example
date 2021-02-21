package com.springbank.oauth2_server.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("security.oauth2.client")
public class AuthServerProperties {
    private String clientId;
    private String clientSecret;
    private String signingKey;
    private int tokenValidity;
    private int refreshTokenValidity;

}
