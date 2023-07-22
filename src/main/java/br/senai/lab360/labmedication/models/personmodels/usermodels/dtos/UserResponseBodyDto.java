package br.senai.lab360.labmedication.models.personmodels.usermodels.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String maritalStatus;
    private String phoneNumber;
    private String email;
    private String birthplace;
    private String crm;
    private String medicalSpecialty;

}
