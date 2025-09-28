package br.com.sinah.common.jwt;

import br.com.sinah.user.model.UserModel;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class JwtManager {

    public static final String JWT_ROLE_NAME = "role";

    public static final String ROLE_PREFIX = "ROLE_";

    private final JwtEncoder jwtEncoder;

    private final JwtProperties jwtProperties;

    public JwtManager(JwtEncoder jwtEncoder, JwtProperties jwtProperties) {
        this.jwtEncoder = jwtEncoder;
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(UserModel user, UUID refreshTokenUUID) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .id(refreshTokenUUID.toString())
                .issuer(jwtProperties.getIssuerValidator())
                .issuedAt(now)
                .expiresAt(now.plus(jwtProperties.getAccessTokenTtl()))
                .audience(new ArrayList<>() {{
                    add(jwtProperties.getAudienceValidator());
                }})
                .subject(user.getUuid().toString())
                .claim(JWT_ROLE_NAME, user.getRole())
                .build();

        JwtEncoderParameters params = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(params).getTokenValue();
    }
}
