package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.mappers.MedicationMapper;
import br.senai.lab360.labmedication.mappers.PatientMapper;
import br.senai.lab360.labmedication.mappers.UserMapper;
import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPostRequestBodyDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPutRequestBodyDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationResponseDto;
import br.senai.lab360.labmedication.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService {
    //  DI
    private final MedicationRepository medicationRepository;
    private final MedicationMapper mapper;
    private final UserMapper uMapper;
    private final PatientMapper pMapper;

    public MedicationResponseDto saveMedication(MedicationPostRequestBodyDto medicationPostRequestBodyDto) {
        Medication medication = mapper.map(medicationPostRequestBodyDto);
        medication.setAdministrationTimeLog(LocalDateTime.now());
        Medication response = medicationRepository.save(medication);
        MedicationResponseDto responseDto = mapper.mapToMedicationPutResponseBodyDto(response);
        responseDto.setPatientIdDto(pMapper.mapToPatientIdDto(response.getPatient()));
        responseDto.setUserIdDto(uMapper.mapToUserIdDto(response.getUser()));

        return responseDto;
    }

    public List<Medication> listMedications() {
        return medicationRepository.findAll();
    }

    public Medication findByIdOrThrowNotFoundException(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication not found"));
    }

    public MedicationResponseDto replaceMedicationData(Long id,
                                                       MedicationPutRequestBodyDto medicationPutRequestBodyDto) {
        Medication savedMedication = findByIdOrThrowNotFoundException(id);
        Medication medicationToSave = mapper.map(medicationPutRequestBodyDto);
        medicationToSave.setId(id);
        medicationToSave.setMedicineName(savedMedication.getMedicineName());
        medicationToSave.setAdministrationTimeLog(savedMedication.getAdministrationTimeLog());
        medicationRepository.save(medicationToSave);

        return mapper
                .mapToMedicationPutResponseBodyDto(findByIdOrThrowNotFoundException(id));
    }

    public void deleteMedicationById(Long id) {
        medicationRepository.delete(findByIdOrThrowNotFoundException(id));
    }
}
