package br.com.sinah.user.mapper;

import br.com.sinah.user.dto.UserDTO;
import br.com.sinah.user.model.UserModel;

public class UserMapper {
    public static UserDTO toDTO(UserModel model) {
        return new UserDTO(
                model.getUuid(),
                model.getUsername(),
                model.getFirstName(),
                model.getLastName(),
                model.getEmail(),
                model.getRole(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }
}
