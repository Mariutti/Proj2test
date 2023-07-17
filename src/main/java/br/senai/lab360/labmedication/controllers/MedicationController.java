package br.senai.lab360.labmedication.controllers;

import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPostRequestDto;
import br.senai.lab360.labmedication.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medications")
@RequiredArgsConstructor
public class MedicationController {
//  DI
    private final MedicationService medicationService;

    @PostMapping
    public Medication saveMedication(@RequestBody MedicationPostRequestDto medicationPostRequestDto){
        return medicationService.saveMedication(medicationPostRequestDto);
    }

}
