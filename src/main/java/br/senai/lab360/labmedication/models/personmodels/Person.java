package br.senai.lab360.labmedication.models.personmodels;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String completeName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Column(unique=true, nullable = false)
    @Pattern(regexp = "\\d{11}")
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    public String maritalStatus;

    @Column(nullable = false)
    public String phoneNumber;

    @Email
    @Column(unique=true, nullable = false)
    public String email;

    @Column(nullable = false)
    public String birthplace;
}
