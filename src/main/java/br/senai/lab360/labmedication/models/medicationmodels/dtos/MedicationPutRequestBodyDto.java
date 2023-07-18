package br.senai.lab360.labmedication.models.medicationmodels.dtos;

import lombok.Data;

@Data
public class MedicationPutRequestBodyDto {

    private String type;

    private int quantity;

    private String unityMeasure;

    private String notes;


}