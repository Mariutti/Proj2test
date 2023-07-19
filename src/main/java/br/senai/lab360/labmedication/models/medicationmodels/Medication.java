package br.senai.lab360.labmedication.models.medicationmodels;

import br.senai.lab360.labmedication.models.adressmodels.Address;
import br.senai.lab360.labmedication.models.personmodels.patientmodels.Patient;
import br.senai.lab360.labmedication.models.personmodels.usermodels.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "medications")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String medicineName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime administrationTimeLog;

    @Column
    private String type;

    @Column
    private int quantity;

    @Column
    private String unityMeasure;

    @Column
    private String notes;

    @ManyToOne
    @JoinColumn(name = "patient_id", unique = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = false)
    private User user;

}
