package br.senai.lab360.labmedication.controllers;

import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPostRequestBodyDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPutRequestBodyDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPutResponseBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPutRequestBodyDto;
import br.senai.lab360.labmedication.services.MedicationService;
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
@RequestMapping("medications")
@RequiredArgsConstructor
public class MedicationController {
    //  DI
    private final MedicationService medicationService;

    @PostMapping
    public ResponseEntity<Medication> saveMedication(@RequestBody MedicationPostRequestBodyDto medicationPostRequestBodyDto) {
        try {
            return new ResponseEntity<Medication>(medicationService.saveMedication(medicationPostRequestBodyDto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Duplicated data", ex);
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationPutResponseBodyDto> replaceMedicationData(
            @PathVariable Long id, @RequestBody MedicationPutRequestBodyDto medicationPutRequestBodyDto) {
        try {
            return new ResponseEntity<MedicationPutResponseBodyDto>(medicationService.
                    replaceMedicationData(id, medicationPutRequestBodyDto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Duplicated data", ex);
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        }
    }

    @GetMapping
    public List<Medication> listMedications() {
        return medicationService.listMedications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> showMedicationById(@PathVariable Long id) {
        return ResponseEntity.ok(medicationService.findByIdOrThrowNotFoundException(id));
    }

}