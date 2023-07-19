package br.senai.lab360.labmedication.controllers;

import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationResponseDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientResponseBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPutRequestBodyDto;
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
    private ResponseEntity<PatientResponseBodyDto> savePatient(@RequestBody @Valid PatientPostRequestBodyDto patientPostRequestBodyDto) {
        try {
            return new ResponseEntity<PatientResponseBodyDto>(patientService.savePatient(patientPostRequestBodyDto), HttpStatus.CREATED);
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
            return new ResponseEntity<PatientResponseBodyDto>(patientService.
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
    public List<PatientResponseBodyDto> getPatients(@RequestParam(required = false, name = "name") String name) {
        return patientService.findAllByName(name);
    }

    //  S07
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseBodyDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.findPatientByIdToDto(id));
    }

    @GetMapping("/{id}/medications")
    public List<MedicationResponseDto> findMedications(@PathVariable Long id){
        return patientService.findMedicationsForPatient(id);
    }

    //  S08
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

}
