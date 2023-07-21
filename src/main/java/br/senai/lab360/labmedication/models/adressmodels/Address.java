package br.senai.lab360.labmedication.models.adressmodels;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresses")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String zipcode;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String street;

    @Column
    private int number;

    @Column
    private String additionalAddressDetails;

    @Column
    private String district;

    @Column
    private String landmark;

}
