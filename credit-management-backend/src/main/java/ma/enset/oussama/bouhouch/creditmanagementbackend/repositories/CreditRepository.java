package ma.enset.oussama.bouhouch.creditmanagementbackend.repositories;

import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Credit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByStatus(CreditStatus status);
    List<Credit> findByClientId(Long clientId);
}