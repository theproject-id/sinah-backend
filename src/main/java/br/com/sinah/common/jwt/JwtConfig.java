package br.com.sinah.common.jwt;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Arrays;

@Configuration
public class JwtConfig {

    private final RsaKeysProperty rsaKeysProperty;

    private final JwtProperties jwtProperties;

    public JwtConfig(RsaKeysProperty rsaKeysProperty, JwtProperties jwtProperties) {
        this.rsaKeysProperty = rsaKeysProperty;
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        var decoder = NimbusJwtDecoder.withPublicKey(rsaKeysProperty.getPublicKey()).build();
        decoder.setJwtValidator(tokenValidator());
        return decoder;
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        var jwk = new RSAKey
                .Builder(rsaKeysProperty.getPublicKey())
                .privateKey(rsaKeysProperty.getPrivateKey())
                .build();

        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName(JwtManager.JWT_ROLE_NAME);
        grantedAuthoritiesConverter.setAuthorityPrefix(JwtManager.ROLE_PREFIX);

        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    public OAuth2TokenValidator<Jwt> tokenValidator() {
        var validators = Arrays.asList(
                new JwtTimestampValidator(),
                new JwtIssuerValidator(jwtProperties.getIssuerValidator()),
                new JwtAudienceValidator(jwtProperties.getAudienceValidator())
        );
        return new DelegatingOAuth2TokenValidator<>(validators);
    }
}