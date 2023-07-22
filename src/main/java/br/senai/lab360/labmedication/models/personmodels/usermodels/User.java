package br.senai.lab360.labmedication.models.personmodels.usermodels;

import br.senai.lab360.labmedication.models.personmodels.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends Person {


    @Column(nullable = false)
    private String crm;
    @Column(nullable = false)
    private String medicalSpecialty;
    @Column(nullable = false)
    @Size(min = 8)
    private String password;
}


