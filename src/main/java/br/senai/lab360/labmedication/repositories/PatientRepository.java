package br.senai.lab360.labmedication.repositories;

import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationResponseDto;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(
            value = "SELECT * FROM PATIENTS p WHERE p.complete_name ilike %?1%",
            nativeQuery = true
    )
    List<Patient> findAllByName(String name);


    @Query(
            value =
//                    "SELECT * FROM PATIENTS LEFT JOIN MEDICATIONS ON MEDICATIONS.PATIENT_ID = :id"
            "SELECT PATIENTS.* FROM PATIENTS LEFT JOIN MEDICATIONS ON MEDICATIONS.PATIENT_ID = :id GROUP BY PATIENTS.ID"
            ,
            nativeQuery = true
    )
    List<Patient> findAllIdMedications(Long id);
}
