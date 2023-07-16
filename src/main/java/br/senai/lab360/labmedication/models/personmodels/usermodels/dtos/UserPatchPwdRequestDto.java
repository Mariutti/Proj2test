package br.senai.lab360.labmedication.models.personmodels.usermodels.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserPatchPwdRequestDto {

    private String oldPwd;

    @NotBlank
    private String newPwd;

}
