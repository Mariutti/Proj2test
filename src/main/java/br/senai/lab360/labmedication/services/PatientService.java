package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.mappers.AdressMapper;
import br.senai.lab360.labmedication.mappers.PatientMapper;
import br.senai.lab360.labmedication.models.adressmodels.dtos.AddressIdDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.PatientDataInfoResponse;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientResponseBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPutRequestBodyDto;
import br.senai.lab360.labmedication.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final AdressMapper adressMapper;

    public List<PatientResponseBodyDto> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::mapToPatientResponseBodyDto)
                .collect(Collectors.toList());
    }

    //  S04
    public PatientResponseBodyDto savePatient(PatientPostRequestBodyDto patientPostRequestBodyDto) {

        Patient patientToSave = patientMapper.map(patientPostRequestBodyDto);
        PatientResponseBodyDto patienteSaved = patientMapper
                .mapToPatientResponseBodyDto(patientRepository.save(patientToSave));
        AddressIdDto addressIdDto = adressMapper.mapToAddressIdDto(patientPostRequestBodyDto.getAddress());
        patienteSaved.setAddress(addressIdDto);

        return patienteSaved;
    }

    public Patient findByIdOrThrowNotFoundException(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    public PatientResponseBodyDto findPatientByIdToDto(Long id) {
        return patientMapper.mapToPatientResponseBodyDto(findByIdOrThrowNotFoundException(id));

    }

    //  S05
    public PatientResponseBodyDto replacePatientData(Long id, PatientPutRequestBodyDto patientPutRequestBodyDto) {
        Patient savedPatient = findByIdOrThrowNotFoundException(id);
        Patient patient = patientMapper.map(patientPutRequestBodyDto);
        patient.setId(id);

        return patientMapper.mapToPatientResponseBodyDto(patientRepository.save(patient));
    }

    public List<PatientResponseBodyDto> findAllByName(String name) {
        if (name != null) {
            List<Patient> patientList = patientRepository.findAllByName(name);
            if (patientList == null || patientList.size() == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no Pacient with " + name +
                        " in their name");
            }
            return patientList
                    .stream()
                    .map(patientMapper::mapToPatientResponseBodyDto).collect(Collectors.toList());
        }
        List<PatientResponseBodyDto> patientList = patientRepository.findAll()
                .stream()
                .map(patientMapper::mapToPatientResponseBodyDto)
                .collect(Collectors.toList());
        return patientList;
    }

    private List<Patient> allMedicationsByPatient(Long id) {
        return patientRepository.findAllIdMedications(id);
    }

    public void deletePatient(Long id) throws Exception {
        Patient patientToDelete = findByIdOrThrowNotFoundException(id);
        //TODO S08 verificar medicação administrada
        allMedicationsByPatient(id);
        patientRepository.delete(patientToDelete);
    }

    public List<PatientDataInfoResponse> getDataInfo() {

        List<PatientDataInfoResponse> result = patientRepository.getDataInfo();
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
