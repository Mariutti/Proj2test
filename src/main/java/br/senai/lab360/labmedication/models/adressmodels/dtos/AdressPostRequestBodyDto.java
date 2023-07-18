package br.senai.lab360.labmedication.models.adressmodels.dtos;

import lombok.Data;

@Data
public class AdressPostRequestBodyDto {
    private String zipcode;
    private String city;
    private String state;
    private String street;
    private int number;
    private String additionalAddressDetails;
    private String district;
    private String landmark;
}
