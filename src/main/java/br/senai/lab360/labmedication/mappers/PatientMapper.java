package br.senai.lab360.labmedication.mappers;

import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientIdDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPostResponseBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPutRequestBodyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient map(PatientPostRequestBodyDto source);
    PatientPostRequestBodyDto mapToPatientPostRequestBodyDto(Patient source);

    Patient map(PatientPostResponseBodyDto source);
    PatientPostResponseBodyDto mapToPatientPostResponseBodyDto(Patient source);

    Patient map(PatientPutRequestBodyDto source);

    PatientIdDto mapToPatientIdDto(Patient source);
}
