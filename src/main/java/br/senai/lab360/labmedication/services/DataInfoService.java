package br.senai.lab360.labmedication.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senai.lab360.labmedication.mappers.PatientMapper;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientDataInfoResponseDto;
import br.senai.lab360.labmedication.repositories.DataInfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataInfoService {

    private final DataInfoRepository dataInfoRepository;
    private final PatientMapper patientMapper;

    

    public List<PatientDataInfoResponseDto> getDataInfo() {

        List<PatientDataInfoResponseDto> result = dataInfoRepository.getDataInfo();
//        List<PatientDataInfoRespondeDto> response = result.stream().map(
//                (patient) -> {
//                    PatientDataInfoRespondeDto responseDto = patientMapper.mapToPatientDataInfoRespondeDto(patient);
//                    responseDto.setId(patient.getId());
//                    responseDto.setTotalMedication(patient.
//
//                    );
//                    return responseDto;
//                }
//        ).collect(Collectors.toList());
//                patientRepository.getDataInfo();
//
//         patientRepository.getDataInfo();
        return result;
    }
}
