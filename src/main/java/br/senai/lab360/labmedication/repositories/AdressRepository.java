package br.senai.lab360.labmedication.repositories;

import br.senai.lab360.labmedication.models.adressmodels.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Address, Long> {
}
