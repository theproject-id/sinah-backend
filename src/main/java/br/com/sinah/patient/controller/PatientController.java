package br.com.sinah.patient.controller;

import br.com.sinah.patient.dto.PatientRequestDTO;
import br.com.sinah.patient.dto.PatientResponseDTO;
import br.com.sinah.patient.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Pacientes", description = "Operações relacionadas a pacientes")
@AllArgsConstructor
@RequestMapping("/api/v1/patients")
@RestController
public class PatientController {
    private final PatientService patientService;

    @Operation(summary = "Criar um novo paciente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Paciente criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
            })
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO request) {
        PatientResponseDTO response = patientService.create(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar paciente por UUID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
                    @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
            })
    @GetMapping("/{uuid}")
    public ResponseEntity<PatientResponseDTO> getPatientByUuid(@PathVariable UUID uuid) {
        PatientResponseDTO response = patientService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os pacientes")
    @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> listPatients() {
        List<PatientResponseDTO> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @Operation(summary = "Atualizar um paciente existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    @PutMapping("/{uuid}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable UUID uuid, @RequestBody PatientRequestDTO request) {
        PatientResponseDTO response = patientService.update(uuid, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deletar paciente por UUID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Paciente deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
            })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID uuid) {
        patientService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
