package br.senai.lab360.labmedication.models.medicationmodels.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class MedicationPutResponseBodyDto {

    private Long id;

    private String medicineName;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime administrationTimeLog;

    private String type;

    private int quantity;

    private String unityMeasure;

    private String notes;

//    private Long idPatient;

}