package br.senai.lab360.labmedication.models.personmodels.usermodels.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserPostRequestBodyDto {
    @NotBlank
    private String completeName;

    @NotBlank
    private String gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
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

    @NotBlank
    private String crm;

    @NotBlank
    private String medicalSpecialty;

    @NotBlank
    private String password;
}
