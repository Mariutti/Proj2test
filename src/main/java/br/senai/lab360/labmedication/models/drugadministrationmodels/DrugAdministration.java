package br.senai.lab360.labmedication.models.drugadministrationmodels;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "drug_administration")
public class DrugAdministration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicineName;
    private Date administrationTimeLog;
    private String type;
    private int quantity;
    private String unityMeasure;
    private String notes;

    private Long idPatient;
    private Long idMd;

}
