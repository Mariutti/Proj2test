package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.mappers.AdressMapper;
import br.senai.lab360.labmedication.mappers.MedicationMapper;
import br.senai.lab360.labmedication.mappers.PatientMapper;
import br.senai.lab360.labmedication.models.adressmodels.dtos.AddressIdDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationResponseDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientResponseBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPutRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserResponseBodyDto;
import br.senai.lab360.labmedication.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper mapper;
    private final AdressMapper aMapper;
    private final MedicationMapper mMapper;

    public List<PatientResponseBodyDto> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(mapper::mapToPatientResponseBodyDto)
                .collect(Collectors.toList());
    }

    //  S04
    public PatientResponseBodyDto savePatient(PatientPostRequestBodyDto patientPostRequestBodyDto) {

        Patient savedPatient = patientRepository.save(mapper.map((patientPostRequestBodyDto)));
        AddressIdDto addressIdDto = aMapper.mapToAddressIdDto(savedPatient.getAddress());
        PatientResponseBodyDto response = mapper.mapToPatientResponseBodyDto(savedPatient);
        response.setAddress(addressIdDto);

        return response;
    }

    public Patient findByIdOrThrowNotFoundException(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    public PatientResponseBodyDto findPatientByIdToDto(Long id){
        return mapper.mapToPatientResponseBodyDto(findByIdOrThrowNotFoundException(id));
    }
    //  S05
    public PatientResponseBodyDto replacePatientData(Long id, PatientPutRequestBodyDto patientPutRequestBodyDto) {
        Patient savedPatient = findByIdOrThrowNotFoundException(id);
        Patient patient = mapper.map(patientPutRequestBodyDto);
        patient.setId(id);
//        patientRepository.save(patient);

        return mapper.mapToPatientResponseBodyDto(patientRepository.save(patient));

    }

    public List<PatientResponseBodyDto> findAllByName(String name) {
        if (name != null) {
            List<Patient> patients = patientRepository.findAllByName(name);
            return patients
                    .stream()
                    .map(mapper::mapToPatientResponseBodyDto).collect(Collectors.toList());
        }
        return patientRepository.findAll()
                .stream()
                .map(mapper::mapToPatientResponseBodyDto).collect(Collectors.toList());
    }

    public void deletePatient(Long id) {
        Patient patientToDelete = findByIdOrThrowNotFoundException(id);
        //TODO S08 verificar medicação administrada
//        List<MedicationResponseDto> medications = patientRepository.listMedications(id)
//                .stream()
//                .map(mMapper::mapToMedicationPutResponseBodyDto)
//                .collect(Collectors.toList());
        patientRepository.delete(patientToDelete);

    }


    public List<MedicationResponseDto> findMedicationsForPatient(Long id){
        return patientRepository.listMedications(id)
                .stream()
                .map(mMapper::mapToMedicationResponseDto)
                .collect(Collectors.toList());
    }
}
