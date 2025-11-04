package br.com.sinah.ward.controller;

import br.com.sinah.ward.dto.WardRequestDTO;
import br.com.sinah.ward.dto.WardResponseDTO;
import br.com.sinah.ward.service.WardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wards")
@Tag(name = "Alas", description = "Gerenciamento de alas hospitalares")
public class WardController {

    private final WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @Operation(summary = "Listar todas as alas")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")})
    @GetMapping
    public ResponseEntity<List<WardResponseDTO>> getAllWards() {
        return ResponseEntity.ok(wardService.findAll());
    }

    @Operation(summary = "Buscar uma ala por UUID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ala encontrada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Ala não encontrada")
            })
    @GetMapping("/{uuid}")
    public ResponseEntity<WardResponseDTO> getWardByUuid(
            @Parameter(description = "UUID da ala") @PathVariable UUID uuid) {
        var request = wardService.findByUuid(uuid);
        return ResponseEntity.ok(request);
    }

    @Operation(summary = "Criar uma nova ala")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ala criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
            })
    @PostMapping
    public ResponseEntity<WardResponseDTO> createWard(
            @Parameter(description = "Dados da nova ala") @RequestBody WardRequestDTO request) {
        WardResponseDTO created = wardService.create(request);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Atualiza uma ala existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ala atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Ala não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Dados de atualização inválidos")
            })
    @PutMapping("/{id}")
    public ResponseEntity<WardResponseDTO> update(@PathVariable UUID id, @RequestBody WardRequestDTO dto) {
        return ResponseEntity.ok(wardService.update(id, dto));
    }

    @Operation(summary = "Excluir uma ala por UUID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Ala excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Ala não encontrada")
            })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteWard(
            @Parameter(description = "UUID da ala a ser excluída") @PathVariable UUID uuid) {
        wardService.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
