package ma.enset.oussama.bouhouch.creditmanagementbackend.repositories;

import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}