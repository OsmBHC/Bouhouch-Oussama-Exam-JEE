package ma.enset.oussama.bouhouch.creditmanagementbackend.repositories;

import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.nom LIKE %:keyword% OR c.email LIKE %:keyword%")
    List<Client> searchClients(@Param("keyword") String keyword);
}