package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.mappers.PatientMapper;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientPutRequestBodyDto;
import br.senai.lab360.labmedication.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper mapper;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    //  S04
    public Patient savePatient(PatientPostRequestBodyDto patientPostRequestBodyDto) {
        return patientRepository.save(mapper.map(patientPostRequestBodyDto));
    }

    public Patient findByIdOrThrowNotFoundException(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    //  S05
    public PatientPutRequestBodyDto replacePatientData(Long id, PatientPutRequestBodyDto patientPutRequestBodyDto) {
        Patient savedPatient = findByIdOrThrowNotFoundException(id);
        Patient patient = mapper.map(patientPutRequestBodyDto);
        patient.setId(id);
        patientRepository.save(patient);

        return patientPutRequestBodyDto;

    }

    public List<Patient> findAllByCompleteName(String name) {
        if (name != null) {
            return patientRepository.findAllByName(name);
        }
        return patientRepository.findAll();
    }

    public void deletePatient(Long id) {
        Patient patientToDelete = findByIdOrThrowNotFoundException(id);
        //TODO S08 verificar medicação administrada
        patientRepository.delete(patientToDelete);

    }
}
