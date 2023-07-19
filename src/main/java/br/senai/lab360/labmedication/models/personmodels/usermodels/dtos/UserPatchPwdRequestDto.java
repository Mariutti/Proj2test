package br.senai.lab360.labmedication.models.personmodels.usermodels.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPatchPwdRequestDto {
    @Size(min = 8)
    private String oldPwd;

    @NotBlank
    @Size(min = 8)
    private String newPwd;

}
