package br.senai.lab360.labmedication.mappers;

import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPostRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface MedicationMapper {

    Medication map(MedicationPostRequestDto source);
}
