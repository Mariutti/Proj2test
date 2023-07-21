package br.senai.lab360.labmedication.mappers;

import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.PatientDataInfoResponse;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    Patient map(PatientPostRequestBodyDto source);
    PatientPostRequestBodyDto mapToPatientPostRequestBodyDto(Patient source);

    Patient map(PatientResponseBodyDto source);
    PatientResponseBodyDto mapToPatientResponseBodyDto(Patient source);

    Patient map(PatientPutRequestBodyDto source);

    PatientIdDto mapToPatientIdDto(Patient source);

    PatientDataInfoResponse mapToPatientDataInfoRespondeDto(PatientResponseBodyDto source);
    PatientDataInfoResponse mapToPatientDataInfoRespondeDto(Patient source);
}