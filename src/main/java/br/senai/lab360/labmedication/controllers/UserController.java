package br.senai.lab360.labmedication.controllers;

import br.senai.lab360.labmedication.models.personmodels.usermodels.User;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPatchPwdRequestDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPostRequestBodyDto;
import br.senai.lab360.labmedication.models.personmodels.usermodels.dtos.UserPutRequestBodyDto;
import br.senai.lab360.labmedication.services.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

//  S01
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserPostRequestBodyDto userPostRequestBodyDto) {
        try {
            return new ResponseEntity<User>(userService.save(userPostRequestBodyDto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Duplicated data", ex);
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        }
    }

//  S02
    @PutMapping("/{id}")
    public ResponseEntity<UserPutRequestBodyDto> replaceUserData(
            @PathVariable Long id, @RequestBody @Valid UserPutRequestBodyDto userPutRequestBodyDto) {
        try {
            return new ResponseEntity<UserPutRequestBodyDto>(userService.
                    replaceUserData(id, userPutRequestBodyDto), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Duplicated data", ex);
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid!@ data", ex);
        }
    }

//  S03
    @PatchMapping("/{id}/password")
    public ResponseEntity<UserPostRequestBodyDto> replacePwd(
            @PathVariable Long id, @RequestBody @Valid UserPatchPwdRequestDto userPatchPwdRequestDto){

        try {
            return new ResponseEntity<UserPostRequestBodyDto>(userService.
                    replacePwd(id, userPatchPwdRequestDto), HttpStatus.OK);

        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        }
    }

    @GetMapping
    public List<User> listAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
            return ResponseEntity.ok(userService.findByIdOrThrowNotFoundException(id));

    }

}
