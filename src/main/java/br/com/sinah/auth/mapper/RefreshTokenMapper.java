package br.com.sinah.auth.mapper;

import br.com.sinah.auth.dto.RefreshTokenDTO;
import br.com.sinah.auth.model.RefreshTokenModel;

public class RefreshTokenMapper {
    public static RefreshTokenDTO toDTO(RefreshTokenModel model) {
        return new RefreshTokenDTO(
                model.getUuid(),
                model.getToken(),
                model.getIssuedAt(),
                model.getExpiresAt()
        );
    }
}
