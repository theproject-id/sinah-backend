package br.com.sinah.notification.controller;

import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/notifications")
@Tag(name = "Notificações", description = "Operações relacionadas às notificações do sistema")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "Cria uma nova notificação")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Notificação criada com sucesso"),
                @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                @ApiResponse(responseCode = "404", description = "Paciente ou Ala não encontrados")
            })
    @PostMapping
    public ResponseEntity<NotificationResponseDTO> create(@RequestBody NotificationRequestDTO dto) {
        NotificationResponseDTO created = notificationService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Lista todas as notificações")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Lista de notificações retornada com sucesso")})
    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> findAll() {
        return ResponseEntity.ok(notificationService.findAll());
    }

    @Operation(summary = "Busca uma notificação por ID")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Notificação encontrada"),
                @ApiResponse(responseCode = "404", description = "Notificação não encontrada")
            })
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(notificationService.findById(id));
    }

    @Operation(summary = "Atualiza uma notificação existente")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Notificação atualizada com sucesso"),
                @ApiResponse(responseCode = "404", description = "Notificação não encontrada"),
                @ApiResponse(responseCode = "400", description = "Dados de atualização inválidos")
            })
    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> update(
            @PathVariable UUID id, @RequestBody NotificationRequestDTO dto) {
        return ResponseEntity.ok(notificationService.update(id, dto));
    }

    @Operation(summary = "Remove uma notificação por ID")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Notificação removida com sucesso"),
                @ApiResponse(responseCode = "404", description = "Notificação não encontrada")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
