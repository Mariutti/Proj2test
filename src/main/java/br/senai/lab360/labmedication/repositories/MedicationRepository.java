package br.senai.lab360.labmedication.repositories;

import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import br.senai.lab360.labmedication.models.medicationmodels.dtos.MedicationDIDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findAllByUserId(Long id);



    @Query(value = """
            SELECT P.ID AS patientId, COUNT(M.ID) AS totalMedication
            FROM MEDICATIONS M
            FULL JOIN PATIENTS P
            ON P.ID = M.PATIENT_ID
            GROUP BY P.ID
            ORDER BY patientId ASC
            """,
            nativeQuery = true
    )
    List<MedicationDIDto> countUsersMedications();

}
