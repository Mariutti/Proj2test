package br.senai.lab360.labmedication.models.medicationmodels.dtos;

import jakarta.persistence.Column;

import java.sql.Timestamp;
import java.util.Date;

public class MedicationPostRequestDto {
    private String medicineName;

    private Date administrationTimeLog;

    private String type;

    private int quantity;

    private String unityMeasure;

    private String notes;

    private Long idPatient;

    private Long idMd;
}
