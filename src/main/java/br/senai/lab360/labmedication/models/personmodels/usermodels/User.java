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
public class User extends Person{


        @Column(nullable = false)
        private String crm;
        @Column(nullable = false)
        private String medicalSpecialty;
        @Column(nullable = false)
        @Size(min = 8)
        private String password;
    }
//método Put
//S02 - Atualização dos dados de Usuários (Médicos)
//● Serviço para alterar/atualizar os dados de determinado usuário.
//● O usuário do sistema poderá alterar sempre que necessário.
//● A senha não deve ser atualizada
//● Definição do Endpoint:
//○ Request:
//■ HTTP PUT no path /api/usuarios/{identificador}
//■ No corpo da request, informar objeto json com os campos.
//■ Os campos validados como sendo obrigatórios devem possuir os valores
//possíveis para estes campos.
//○ Response:
//■ HTTP Status Code 200 (OK) em caso de sucesso, constando no corpo da
//resposta os dados atualizados do médico.
//■ HTTP Status Code 400 (Bad Request) em caso de requisição com dados
//inválidos, informando mensagem de erro explicativa no corpo do
//response.
//■ HTTP Status Code 404 (Not Found) em caso de não ser encontrado
//registro com o código informado, retornando mensagem de erro
//explicativa no corpo do response.

