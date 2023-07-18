package br.senai.lab360.labmedication.controllers;

import br.senai.lab360.labmedication.models.adressmodels.Address;
import br.senai.lab360.labmedication.models.adressmodels.dtos.AdressPostRequestBodyDto;
import br.senai.lab360.labmedication.services.AdressService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("adresses")
@RequiredArgsConstructor
public class AdressController {

    private final AdressService adressService;

    @PostMapping
    public ResponseEntity<Address> saveAdress(@RequestBody AdressPostRequestBodyDto adress) {
        try {
            return new ResponseEntity<Address>(adressService.save(adress), HttpStatus.CREATED);

        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        }
    }

    @GetMapping
    public List<Address> listAllAdresses(){
        return adressService.listAll();
    }

}
