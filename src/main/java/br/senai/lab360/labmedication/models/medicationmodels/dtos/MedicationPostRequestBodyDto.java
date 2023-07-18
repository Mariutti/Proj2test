package br.senai.lab360.labmedication.models.medicationmodels.dtos;

import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.usermodels.User;
import lombok.Data;

@Data
public class MedicationPostRequestBodyDto {
    private Long id;

    private String medicineName;

    private String type;

    private int quantity;

    private String unityMeasure;

    private String notes;

    private Patient patient;

    private User user;
}
