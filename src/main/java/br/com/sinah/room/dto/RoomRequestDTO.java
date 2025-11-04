package br.com.sinah.room.dto;

import java.util.UUID;

public record RoomRequestDTO(String name, String description, UUID wardUuid) {
}
