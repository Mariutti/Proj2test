package br.senai.lab360.labmedication.repositories;

import br.senai.lab360.labmedication.models.personmodels.usermodels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
