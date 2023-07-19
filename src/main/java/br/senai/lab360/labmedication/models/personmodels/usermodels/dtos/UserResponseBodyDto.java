package br.senai.lab360.labmedication.models.personmodels.usermodels.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponseBodyDto {

    private Long id;
    private String completeName;
    private String gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;

    private String cpf;

    private String rg;
    public String maritalStatus;
    public String phoneNumber;
    public String email;
    public String birthplace;
    private String crm;
    private String medicalSpecialty;

}
