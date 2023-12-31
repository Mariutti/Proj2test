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
    private final PatientService patientService;

    public MedicationResponseDto saveMedication(MedicationPostRequestBodyDto medicationPostRequestBodyDto) {
        Medication medication = mapper.map(medicationPostRequestBodyDto);
        medication.setAdministrationTimeLog(LocalDateTime.now());

        return convertUserAndPatient(medicationRepository.save(medication));
    }

    public List<MedicationResponseDto> listMedications() {
        return medicationRepository.findAll()
                .stream()
                .map(this::convertUserAndPatient).toList();
    }

    public Medication findByIdOrThrowNotFoundException(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication not found"));
    }

    public MedicationResponseDto findMedicationByIdToDto(Long id) {
        return convertUserAndPatient(findByIdOrThrowNotFoundException(id));
    }

    private MedicationResponseDto convertUserAndPatient(Medication medicationFounded) {
        MedicationResponseDto response = mapper.mapToMedicationResponseDto(medicationFounded);
        response.setPatientIdDto(pMapper.mapToPatientIdDto(medicationFounded.getPatient()));
        response.setUserIdDto(uMapper.mapToUserIdDto(medicationFounded.getUser()));
        return response;
    }

    public MedicationResponseDto replaceMedicationData(Long id,
                                                       MedicationPutRequestBodyDto medicationPutRequestBodyDto) {
        Medication savedMedication = findByIdOrThrowNotFoundException(id);
        Medication medicationToSave = mapper.map(medicationPutRequestBodyDto);
        medicationToSave.setId(id);
        medicationToSave.setMedicineName(savedMedication.getMedicineName());
        medicationToSave.setAdministrationTimeLog(savedMedication.getAdministrationTimeLog());
        medicationRepository.save(medicationToSave);

        return convertUserAndPatient(findByIdOrThrowNotFoundException(id));
    }

    public void deleteMedicationById(Long id) {
        medicationRepository.delete(findByIdOrThrowNotFoundException(id));
    }

    public List<MedicationResponseDto> findMedicationsByPatient(Long id) {
        List<Medication> responses = medicationRepository.findAllByUserId(id);

        patientService.findByIdOrThrowNotFoundException(id);
        
        if(responses == null || responses.isEmpty()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacient with ID: " + id +" has no medications " +
                    "saved.");
        }
        else{
            return responses.stream()
                    .map(this::convertUserAndPatient).toList();
        }
    }
}