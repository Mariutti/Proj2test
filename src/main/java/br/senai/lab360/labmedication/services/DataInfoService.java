package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.models.personmodels.patientmodels.PatientDataInfoResponse;
import br.senai.lab360.labmedication.repositories.DataInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataInfoService {

    @Autowired
    DataInfoRepository dataInfoRepository;

    public List<PatientDataInfoResponse> getDataInfo() {
        return dataInfoRepository.getDataInfo();
    }
}
