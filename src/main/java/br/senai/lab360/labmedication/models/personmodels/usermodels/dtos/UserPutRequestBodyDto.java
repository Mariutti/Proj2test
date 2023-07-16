package br.senai.lab360.labmedication.models.personmodels.usermodels.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserPutRequestBodyDto {

    @NotBlank
    private String completeName;

    @NotBlank
    private String gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date birthdate;

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;

    @NotBlank
    public String maritalStatus;

    @NotBlank
    public String phoneNumber;

    @Email
    @NotBlank
    public String email;

    @NotBlank
    public String birthplace;

    @NotBlank
    private String crm;

    @NotBlank
    private String medicalSpecialty;
}
