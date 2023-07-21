package br.senai.lab360.labmedication.models.personmodels.patientmodels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PatientDataInfoResponse {

    @Id
    private Long patientId;

    private int totalMedication;

}
