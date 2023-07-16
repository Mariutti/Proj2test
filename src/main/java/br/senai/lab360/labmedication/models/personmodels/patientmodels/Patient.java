package br.senai.lab360.labmedication.models.personmodels.patientmodels;

import br.senai.lab360.labmedication.models.personmodels.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Patient extends Person {

    @Column
    private String emergencyContact;

    @Column
    private List<String> allergies;

    @Column
    private List<String> especificCares;

    @Column
    private String healthCare;

    @Column
    private String healthCareIdNumb;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date healthCareExpiration;

    @Column
    private String adress;

}
//Contato de Emergência: Obrigatório. Deve ser texto
//○ Lista de Alergias: Não obrigatório. Deve ser texto
//○ Lista de Cuidados Específicos: Não obrigatório. Deve ser texto
//5
//○ Convênio: Não obrigatório. Deve ser texto
//○ Número da Carteira do Convênio: Não obrigatório. Deve ser texto
//○ Validade da Carteira do Convênio: Não Obrigatório, data válida.
//○ Endereço: Este deve ser uma referência a tabela endereço, numa requisição o endereço deve ser identificado pelo
// Identificador do Endereço no Banco de Dados (identificador endereço)