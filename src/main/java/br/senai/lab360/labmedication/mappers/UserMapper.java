package br.senai.lab360.labmedication.mappers;

import br.senai.lab360.labmedication.models.personmodels.usermodels.User;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserIdDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPutRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserResponseBodyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)

    public interface UserMapper {
    User map(UserPostRequestBodyDto source);

    User map(UserPutRequestBodyDto source);

    User map(UserResponseBodyDto source);

    UserPostRequestBodyDto map(User source);

    UserIdDto mapToUserIdDto(User source);

    UserResponseBodyDto mapToUserResponseBodyDto(User source);
}