package br.senai.lab360.labmedication.services;

import br.senai.lab360.labmedication.mappers.AdressMapper;
import br.senai.lab360.labmedication.models.adressmodels.Address;
import br.senai.lab360.labmedication.models.adressmodels.dtos.AdressPostRequestBodyDto;
import br.senai.lab360.labmedication.repositories.AdressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdressService {

    private final AdressRepository adressRepository;
    private final AdressMapper mapper;

    public Address save(AdressPostRequestBodyDto adress) {
        Address addressToSave = mapper.map(adress);
        return adressRepository.save(addressToSave);
    }

    public List<Address> listAll() {
        return adressRepository.findAll();
    }
}
