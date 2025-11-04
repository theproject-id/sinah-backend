package br.com.sinah.bed.dto;

import java.util.UUID;

public record BedRequestDTO(int number, UUID roomUuid) {
}
