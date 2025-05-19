package ma.enset.oussama.bouhouch.creditmanagementbackend.mappers;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.CreditDTO;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.*;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.CreditStatus;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.PropertyType;

public class CreditMapper {
    public static CreditDTO toDTO(Credit credit) {
        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setRequestDate(credit.getRequestDate());
        dto.setStatus(credit.getStatus().name());
        dto.setAcceptationDate(credit.getAcceptationDate());
        dto.setAmount(credit.getAmount());
        dto.setDuration(credit.getDuration());
        dto.setInterestRate(credit.getInterestRate());
        dto.setClientId(credit.getClient().getId());

        if (credit instanceof PersonalCredit) {
            dto.setType("PERSONAL");
            dto.setMotif(((PersonalCredit) credit).getMotif());
        } else if (credit instanceof ImmobilierCredit) {
            dto.setType("IMMOBILIER");
            dto.setPropertyType(((ImmobilierCredit) credit).getType().name());
        } else if (credit instanceof ProfessionnelCredit) {
            dto.setType("PROFESSIONNEL");
            dto.setMotif(((ProfessionnelCredit) credit).getMotif());
            dto.setCompanyName(((ProfessionnelCredit) credit).getCompanyName());
        }
        return dto;
    }

    public static Credit toEntity(CreditDTO dto) {
        Credit credit;
        switch (dto.getType()) {
            case "PERSONAL":
                credit = new PersonalCredit();
                ((PersonalCredit) credit).setMotif(dto.getMotif());
                break;
            case "IMMOBILIER":
                credit = new ImmobilierCredit();
                ((ImmobilierCredit) credit).setType(PropertyType.valueOf(dto.getPropertyType()));
                break;
            case "PROFESSIONNEL":
                credit = new ProfessionnelCredit();
                ((ProfessionnelCredit) credit).setMotif(dto.getMotif());
                ((ProfessionnelCredit) credit).setCompanyName(dto.getCompanyName());
                break;
            default:
                throw new IllegalArgumentException("Type de crédit invalide : " + dto.getType());
        }
        credit.setId(dto.getId());
        credit.setRequestDate(dto.getRequestDate());
        credit.setStatus(CreditStatus.valueOf(dto.getStatus()));
        credit.setAcceptationDate(dto.getAcceptationDate());
        credit.setAmount(dto.getAmount());
        credit.setDuration(dto.getDuration());
        credit.setInterestRate(dto.getInterestRate());
        // La relation avec Client sera définie dans la couche service
        return credit;
    }
}