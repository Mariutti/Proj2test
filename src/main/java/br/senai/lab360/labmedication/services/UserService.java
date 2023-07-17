package br.senai.lab360.labmedication.services;


import br.senai.lab360.labmedication.mappers.UserMapper;
import br.senai.lab360.labmedication.models.personmodels.usermodels.User;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPatchPwdRequestDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPutRequestBodyDto;
import br.senai.lab360.labmedication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
//  DI
    private final UserRepository userRepository;
//  DI
    private final UserMapper mapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

//  S01
    public User save(UserPostRequestBodyDto userPostRequestBodyDto) {
        return userRepository.save(mapper.map(userPostRequestBodyDto));
    }

    public User findByIdOrThrowNotFoundException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

//  S02
    public UserPutRequestBodyDto replaceUserData(Long id, UserPutRequestBodyDto userPutRequestBodyDto) {
        User savedUser = findByIdOrThrowNotFoundException(id);
        User user = mapper.map(userPutRequestBodyDto);
        user.setId(id);
        user.setPassword(savedUser.getPassword());
        userRepository.save(user);

        return userPutRequestBodyDto;

    }

//  S03
    public UserPostRequestBodyDto replacePwd(Long id, UserPatchPwdRequestDto userPatchPwdRequestDto) {
        User savedUserPwd = findByIdOrThrowNotFoundException(id);
        if (userPatchPwdRequestDto.getOldPwd() == null
                || savedUserPwd.getPassword().equals(userPatchPwdRequestDto.getOldPwd())
                || savedUserPwd.getPassword().isEmpty()
                || savedUserPwd.getPassword().isBlank()
        ) {
            savedUserPwd.setPassword(userPatchPwdRequestDto.getNewPwd());
            userRepository.save(savedUserPwd);
            return mapper.map(savedUserPwd);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Password invalid");
        }
    }
}
