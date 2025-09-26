package br.com.sinah.common.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String issuerValidator;

    private String audienceValidator;

    private Duration accessTokenTtl;

    private Duration refreshTokenTtl;

    public String getIssuerValidator() {
        return issuerValidator;
    }

    public void setIssuerValidator(String issuerValidator) {
        this.issuerValidator = issuerValidator;
    }

    public String getAudienceValidator() {
        return audienceValidator;
    }

    public void setAudienceValidator(String audienceValidator) {
        this.audienceValidator = audienceValidator;
    }

    public Duration getAccessTokenTtl() {
        return accessTokenTtl;
    }

    public void setAccessTokenTtl(Duration accessTokenTtl) {
        this.accessTokenTtl = accessTokenTtl;
    }

    public Duration getRefreshTokenTtl() {
        return refreshTokenTtl;
    }

    public void setRefreshTokenTtl(Duration refreshTokenTtl) {
        this.refreshTokenTtl = refreshTokenTtl;
    }
}
