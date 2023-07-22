package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationDIDto;
import br.senai.lab360.labmedication.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationDataInfoService {

    private final MedicationRepository medicationRepository;

    public List<MedicationDIDto> getDataInfo() {
        return medicationRepository.countUsersMedications();
    }
}
