package br.senai.lab360.labmedication.models.personmodels.patientmodels.dtos;

import br.senai.lab360.labmedication.models.adressmodels.dtos.AddressIdDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class PatientResponseBodyDto {


    // inheritance from Person
    @NotBlank
    private Long id;

    @NotBlank
    private String completeName;
    @NotBlank
    private String gender;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;

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
    private Date healthCareExpiration;

    private AddressIdDto address;
}
