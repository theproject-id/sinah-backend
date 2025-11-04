package br.com.sinah.bed.controller;

import br.com.sinah.bed.service.BedService;
import br.com.sinah.bed.dto.BedRequestDTO;
import br.com.sinah.bed.dto.BedResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beds")
@AllArgsConstructor
@Tag(name = "Leitos", description = "Gerenciamento de leitos hospitalares")
public class BedController {

    private final BedService bedService;

    @Operation(summary = "Listar todos os leitos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")})
    @GetMapping
    public ResponseEntity<List<BedResponseDTO>> getAllBeds() {
        return ResponseEntity.ok(bedService.findAll());
    }

    @Operation(summary = "Buscar leito por UUID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Leito encontrada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Leito não encontrada")
            })
    @GetMapping("/{uuid}")
    public ResponseEntity<BedResponseDTO> getBedByUuid(
            @Parameter(description = "UUID do leito") @PathVariable UUID uuid) {
        var request = bedService.findByUuid(uuid);
        return ResponseEntity.ok(request);
    }

    @Operation(summary = "Criar um novo leito")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Leito criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
            })
    @PostMapping
    public ResponseEntity<BedResponseDTO> createBed(
            @Parameter(description = "Dados do novo leito") @RequestBody BedRequestDTO request) {
        BedResponseDTO created = bedService.create(request);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Atualizar um leito existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Leito atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Leito não encontrada"),
                    @ApiResponse(responseCode = "400", description = "Dados de atualização inválidos")
            })
    @PutMapping("/{id}")
    public ResponseEntity<BedResponseDTO> update(@PathVariable UUID id, @RequestBody BedRequestDTO dto) {
        return ResponseEntity.ok(bedService.update(id, dto));
    }

    @Operation(summary = "Excluir um leito por UUID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Leito excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Leito não encontrada")
            })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteBed(@Parameter(description = "UUID do leito a ser excluído") @PathVariable UUID uuid) {
        bedService.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
