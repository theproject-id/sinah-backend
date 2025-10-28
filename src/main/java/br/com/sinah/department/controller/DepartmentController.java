package br.com.sinah.department.controller;

import br.com.sinah.department.dto.DepartmentRequestDTO;
import br.com.sinah.department.dto.DepartmentResponseDTO;
import br.com.sinah.department.service.DepartmentService;
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
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
@Tag(name = "Departamentos", description = "Gerenciamento de departamentos hospitalares")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Listar todos os departamentos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")})
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @Operation(summary = "Buscar departamento por UUID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Departamento encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Departamento não encontrado")
            })
    @GetMapping("/{uuid}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentByUuid(
            @Parameter(description = "UUID do departamento") @PathVariable UUID uuid) {
        var request = departmentService.findByUuid(uuid);
        return ResponseEntity.ok(request);
    }

    @Operation(summary = "Criar um novo departamento")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Departamento criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
            })
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @Parameter(description = "Dados do novo departamento") @RequestBody DepartmentRequestDTO request) {
        DepartmentResponseDTO created = departmentService.create(request);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Atualizar um departamento existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Departamento atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Departamento não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Dados de atualização inválidos")
            })
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> update(@PathVariable UUID id, @RequestBody DepartmentRequestDTO dto) {
        return ResponseEntity.ok(departmentService.update(id, dto));
    }

    @Operation(summary = "Excluir um departamento por UUID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Departamento excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Departamento não encontrado")
            })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteDepartment(
            @Parameter(description = "UUID do departamento a ser excluído") @PathVariable UUID uuid) {
        departmentService.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
