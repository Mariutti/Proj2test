package br.senai.lab360.labmedication.mappers;

import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.*;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientMedicationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MedicationMapper {

    Medication map(MedicationPostRequestBodyDto source);

    Medication map(MedicationPutRequestBodyDto source);

    MedicationResponseDto mapToMedicationResponseDto(Medication source);

    PatientMedicationResponseDto mapToPatientMedicationResponseDto(Medication source);

}