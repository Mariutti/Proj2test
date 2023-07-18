package br.senai.lab360.labmedication.mappers;

import br.senai.lab360.labmedication.models.adressmodels.Address;
import br.senai.lab360.labmedication.models.adressmodels.dtos.AdressIdDto;
import br.senai.lab360.labmedication.models.adressmodels.dtos.AdressPostRequestBodyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AdressMapper {

    Address map(AdressPostRequestBodyDto source);

    Address map(AdressIdDto source);

}