package br.senai.lab360.labmedication.repositories;

import br.senai.lab360.labmedication.models.medicationmodels.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
