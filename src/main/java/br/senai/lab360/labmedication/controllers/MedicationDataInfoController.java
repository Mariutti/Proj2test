package br.senai.lab360.labmedication.controllers;

import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationDIDto;
import br.senai.lab360.labmedication.services.MedicationDataInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("datainfos")
@RequiredArgsConstructor
public class MedicationDataInfoController {

    private final MedicationDataInfoService medicationDataInfoService;

    @GetMapping
    public List<MedicationDIDto> getDataInfo() {
        return medicationDataInfoService.getDataInfo();
    }
}
