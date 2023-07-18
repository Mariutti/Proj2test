package br.senai.lab360.labmedication.models.medicationmodels.dtos;

import lombok.Data;

@Data
public class MedicationPostRequestBodyDto {
    private Long id;

    private String medicineName;

    private String type;

    private int quantity;

    private String unityMeasure;

    private String notes;

//    private Long idPatient;
}
