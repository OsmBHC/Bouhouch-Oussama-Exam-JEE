package ma.enset.oussama.bouhouch.creditmanagementbackend.repositories;

import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Remboursement;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.RemboursementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByType(RemboursementType type);
    List<Remboursement> findByCreditId(Long creditId);
}