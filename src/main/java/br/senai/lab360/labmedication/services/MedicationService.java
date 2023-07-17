package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.mappers.MedicationMapper;
import br.senai.lab360.labmedication.mappers.UserMapper;
import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPostRequestDto;
import br.senai.lab360.labmedication.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationService {
//  DI
    private final MedicationRepository medicationRepository;
//  DI
    private final MedicationMapper mapper;

    public Medication saveMedication(MedicationPostRequestDto medicationPostRequestDto) {
        return medicationRepository.save(mapper.map(medicationPostRequestDto));
    }
}
