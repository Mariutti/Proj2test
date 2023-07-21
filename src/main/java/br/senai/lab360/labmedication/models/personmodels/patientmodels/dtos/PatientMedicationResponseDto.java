package br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientMedicationResponseDto {

    private Long id;

    private String medicineName;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime administrationTimeLog;

    private String type;

    private int quantity;

    private String unityMeasure;

    private String notes;

    private Long patientId;

    private Long userId;

}