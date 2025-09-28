package br.com.sinah.common.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String issuerValidator;

    private String audienceValidator;

    private Duration accessTokenTtl;

    private Duration refreshTokenTtl;
}
