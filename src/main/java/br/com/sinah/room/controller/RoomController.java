package br.com.sinah.room.controller;

import br.com.sinah.room.dto.RoomRequestDTO;
import br.com.sinah.room.dto.RoomResponseDTO;
import br.com.sinah.room.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Quartos", description = "Operações relacionadas a quartos")
@AllArgsConstructor
@RequestMapping("/api/v1/rooms")
@RestController
public class RoomController {
    private final RoomService roomService;

    @Operation(summary = "Criar um novo quarto")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Quarto criado com sucesso"),
                @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
            })
    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(@RequestBody RoomRequestDTO request) {
        RoomResponseDTO response = roomService.create(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar quarto por UUID")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Quarto encontrado"),
                @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
            })
    @GetMapping("/{uuid}")
    public ResponseEntity<RoomResponseDTO> getRoomByUuid(@PathVariable UUID uuid) {
        RoomResponseDTO response = roomService.findByUuid(uuid);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os quartos")
    @ApiResponse(responseCode = "200", description = "Lista de quartos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> listRooms() {
        List<RoomResponseDTO> rooms = roomService.findAll();
        return ResponseEntity.ok(rooms);
    }

    @Operation(summary = "Atualizar um quarto existente")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Quarto atualizado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Quarto não encontrado"),
                @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    @PutMapping("/{uuid}")
    public ResponseEntity<RoomResponseDTO> updateRoom(
            @PathVariable UUID uuid, @RequestBody RoomRequestDTO request) {
        RoomResponseDTO response = roomService.update(uuid, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deletar quarto pelo UUID")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Quarto deletado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Quarto não encontrado")
            })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID uuid) {
        roomService.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
