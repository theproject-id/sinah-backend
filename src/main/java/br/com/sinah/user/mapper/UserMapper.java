package br.com.sinah.user.mapper;

import br.com.sinah.auth.dto.UserRegisterDTO;
import br.com.sinah.user.dto.UserDTO;
import br.com.sinah.user.model.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(UserModel model) {
        UserDTO dto = new UserDTO();
        dto.setUuid(model.getUuid());
        dto.setUsername(model.getUsername());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setRole(model.getRole());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        return dto;
    }

    public static List<UserDTO> toDTO(List<UserModel> models) {
        return models.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static UserModel fromRegisterDTO(UserRegisterDTO dto) {
        UserModel model = new UserModel();
        model.setUsername(dto.getUsername());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setPassword(dto.getPassword());
        model.setEmail(dto.getEmail());
        model.setRole(dto.getRole());
        return model;
    }
}
