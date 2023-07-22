package br.senai.lab360.labmedication.controllers;

import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPutRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientResponseBodyDto;
import br.senai.lab360.labmedication.services.PatientService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    //  S04
    @PostMapping
    public ResponseEntity<PatientResponseBodyDto> savePatient(@RequestBody @Valid PatientPostRequestBodyDto patientPostRequestBodyDto) {
        try {
            return new ResponseEntity<>(patientService.savePatient(patientPostRequestBodyDto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Duplicated data: CPF already exists", ex);
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        }
    }

    //  S05
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseBodyDto> replacePatientData(
            @PathVariable Long id, @RequestBody @Valid PatientPutRequestBodyDto patientPutRequestBodyDto) {
        try {
            return new ResponseEntity<>(patientService.
                    replacePatientData(id, patientPutRequestBodyDto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Duplicated data: CPF already exists", ex);
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        }
    }

    //  S06
    @GetMapping
    public ResponseEntity<List<PatientResponseBodyDto>> getPatients(@RequestParam(required = false, name = "name") String name) {
            return ResponseEntity.ok(patientService.findAllByName(name));
    }

    //  S07
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseBodyDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.findPatientByIdToDto(id));
    }


    //  S08
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found", ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient cannot be deleted", ex);
        }
    }

}
