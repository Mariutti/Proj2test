package br.senai.lab360.labmedication.models.medicationmodels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "medications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String medicineName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date administrationTimeLog;

    @Column
    private String type;

    @Column
    private int quantity;

    @Column
    private String unityMeasure;

    @Column
    private String notes;

    @Column
    private Long idPatient;

    @Column
    private Long idMd;

}
