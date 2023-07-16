package br.senai.lab360.labmedication.repositories;

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
}
