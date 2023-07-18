package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.mappers.MedicationMapper;
import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPostRequestBodyDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPutRequestBodyDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPutResponseBodyDto;
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

    public Medication saveMedication(MedicationPostRequestBodyDto medicationPostRequestBodyDto) {
        Medication medication = mapper.mapToMedicationPostRequestBodyDto(medicationPostRequestBodyDto);
        medication.setAdministrationTimeLog(LocalDateTime.now());
        return medicationRepository.save(medication);
    }

    public List<Medication> listMedications() {
        return medicationRepository.findAll();
    }

    public Medication findByIdOrThrowNotFoundException(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication not found"));
    }

    public MedicationPutResponseBodyDto replaceMedicationData(Long id,
                                                              MedicationPutRequestBodyDto medicationPutRequestBodyDto) {
        Medication savedMedication = findByIdOrThrowNotFoundException(id);
        Medication medicationToSave = mapper.mapToMedicationPutRequestBodyDto(medicationPutRequestBodyDto);
        medicationToSave.setId(id);
        medicationToSave.setMedicineName(savedMedication.getMedicineName());
        medicationToSave.setAdministrationTimeLog(savedMedication.getAdministrationTimeLog());
        medicationRepository.save(medicationToSave);

        return mapper
                .mapFromMedicationPutResponseBodyDto(findByIdOrThrowNotFoundException(id));
    }
}
