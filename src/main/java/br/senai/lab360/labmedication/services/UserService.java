package br.senai.lab360.labmedication.services;


import br.senai.lab360.labmedication.mappers.UserMapper;
import br.senai.lab360.labmedication.models.personmodels.usermodels.User;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPatchPwdRequestDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPutRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserResponseBodyDto;
import br.senai.lab360.labmedication.repositories.UserRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    //  DI
    private final UserRepository userRepository;
    //  DI
    private final UserMapper mapper;

    public List<UserResponseBodyDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseBodyDto> listUsersResponseBodyDto = users
                .stream()
                .map(x -> mapper.mapToUserResponseBodyDto(x)).collect(Collectors.toList());

        return listUsersResponseBodyDto;
    }

    //  S01
    public UserResponseBodyDto save(UserPostRequestBodyDto userPostRequestBodyDto) {
        UserResponseBodyDto urd = mapper.mapToUserResponseBodyDto(userRepository.save(mapper.map(userPostRequestBodyDto)));
        return urd;
    }

    public User findByIdOrThrowNotFoundException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public UserResponseBodyDto findUsersByIdToDto(Long id){
        return mapper.mapToUserResponseBodyDto(findByIdOrThrowNotFoundException(id));
    }

    //  S02
    public UserResponseBodyDto replaceUserData(Long id, UserPutRequestBodyDto userPutRequestBodyDto) {
        User savedUser = findByIdOrThrowNotFoundException(id);
        User user = mapper.map(userPutRequestBodyDto);
        user.setId(id);
        user.setPassword(savedUser.getPassword());
        User newUser = userRepository.save(user);

        return mapper.mapToUserResponseBodyDto(newUser);

    }

    //  S03
    public UserResponseBodyDto replacePwd(Long id, UserPatchPwdRequestDto userPatchPwdRequestDto) throws ConstraintViolationException {
        User savedUserPwd = findByIdOrThrowNotFoundException(id);
        if (userPatchPwdRequestDto.getOldPwd().equals(savedUserPwd.getPassword()))
        {
            savedUserPwd.setPassword(userPatchPwdRequestDto.getNewPwd());
            return mapper.mapToUserResponseBodyDto(userRepository.save(savedUserPwd));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Password invalid");
        }
    }
}
