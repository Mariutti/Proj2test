package br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.senai.lab360.labmedication.models.adressmodels.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class PatientPostRequestBodyDto {


    // inheritance from Person
    @NotBlank
    private String completeName;
    @NotBlank
    private String gender;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;
    @NotBlank
    public String maritalStatus;
    @NotBlank
    public String phoneNumber;
    @NotBlank
    public String email;
    @NotBlank
    public String birthplace;



    //atributes only for Patient
    private String emergencyContact;

    private String allergies;

    private String especificCares;

    private String healthCare;

    private String healthCareIdNumb;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate healthCareExpiration;

    private Address address;
}
