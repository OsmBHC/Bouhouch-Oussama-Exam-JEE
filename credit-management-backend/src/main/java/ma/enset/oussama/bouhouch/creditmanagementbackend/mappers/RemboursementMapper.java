package ma.enset.oussama.bouhouch.creditmanagementbackend.mappers;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.RemboursementDTO;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Remboursement;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.RemboursementType;

public class RemboursementMapper {
    public static RemboursementDTO toDTO(Remboursement remboursement) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setAmount(remboursement.getAmount());
        dto.setType(remboursement.getType().name());
        dto.setCreditId(remboursement.getCredit().getId());
        return dto;
    }

    public static Remboursement toEntity(RemboursementDTO dto) {
        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setAmount(dto.getAmount());
        remboursement.setType(RemboursementType.valueOf(dto.getType()));
        // La relation avec Credit sera définie dans la couche service
        return remboursement;
    }
}