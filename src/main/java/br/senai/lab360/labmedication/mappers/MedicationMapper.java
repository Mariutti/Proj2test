package br.senai.lab360.labmedication.mappers;

import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPostRequestBodyDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPutRequestBodyDto;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationPutResponseBodyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MedicationMapper {

    Medication mapToMedicationPostRequestBodyDto(MedicationPostRequestBodyDto source);

    Medication mapToMedicationPutRequestBodyDto(MedicationPutRequestBodyDto source);

    MedicationPutResponseBodyDto mapFromMedicationPutResponseBodyDto(Medication source);

}