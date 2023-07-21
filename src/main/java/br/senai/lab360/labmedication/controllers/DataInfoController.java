package br.senai.lab360.labmedication.controllers;

import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientDataInfoResponseDto;
import br.senai.lab360.labmedication.services.DataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("datainfos")
public class DataInfoController {

    @Autowired
    DataInfoService dataInfoService;

    @GetMapping
    public List<PatientDataInfoResponseDto> getDataInfo(){
        return dataInfoService.getDataInfo();
    }
}
