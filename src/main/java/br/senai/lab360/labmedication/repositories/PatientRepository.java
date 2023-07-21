package br.senai.lab360.labmedication.repositories;

import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos.PatientDataInfoRespondeDto;
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
            "SELECT PATIENTS.* FROM PATIENTS LEFT JOIN MEDICATIONS ON MEDICATIONS.PATIENT_ID = :id GROUP BY PATIENTS.ID"
            ,
            nativeQuery = true
    )
    List<Patient> findAllIdMedications(Long id);

    @Query(
            value = "SELECT P.ID, COUNT(M.ID) AS TOTAL_MEDICATION " +
                    "FROM PATIENTS P " +
                    "LEFT JOIN MEDICATIONS M " +
                    "ON M.PATIENT_ID = P.ID " +
                    "GROUP BY P.ID",
            nativeQuery = true
    )
    List<PatientDataInfoRespondeDto> getDataInfo();

}
