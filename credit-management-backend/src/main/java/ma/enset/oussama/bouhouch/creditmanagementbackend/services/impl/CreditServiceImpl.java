package ma.enset.oussama.bouhouch.creditmanagementbackend.services.impl;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.CreditDTO;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Client;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Credit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.ImmobilierCredit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.PersonalCredit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.ProfessionnelCredit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.CreditStatus;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.PropertyType;
import ma.enset.oussama.bouhouch.creditmanagementbackend.mappers.CreditMapper;
import ma.enset.oussama.bouhouch.creditmanagementbackend.repositories.ClientRepository;
import ma.enset.oussama.bouhouch.creditmanagementbackend.repositories.CreditRepository;
import ma.enset.oussama.bouhouch.creditmanagementbackend.services.CreditService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;

    public CreditServiceImpl(CreditRepository creditRepository, ClientRepository clientRepository) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<CreditDTO> findAll() {
        return creditRepository.findAll().stream()
                .map(CreditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO findById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        return CreditMapper.toDTO(credit);
    }

    @Override
    public CreditDTO create(CreditDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        Credit credit = CreditMapper.toEntity(creditDTO);
        credit.setClient(client);
        credit = creditRepository.save(credit);
        return CreditMapper.toDTO(credit);
    }

    @Override
    public CreditDTO update(Long id, CreditDTO creditDTO) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
        credit.setRequestDate(creditDTO.getRequestDate());
        credit.setStatus(CreditStatus.valueOf(creditDTO.getStatus()));
        credit.setAcceptationDate(creditDTO.getAcceptationDate());
        credit.setAmount(creditDTO.getAmount());
        credit.setDuration(creditDTO.getDuration());
        credit.setInterestRate(creditDTO.getInterestRate());
        if (credit instanceof PersonalCredit && "PERSONAL".equals(creditDTO.getType())) {
            ((PersonalCredit) credit).setMotif(creditDTO.getMotif());
        } else if (credit instanceof ImmobilierCredit && "IMMOBILIER".equals(creditDTO.getType())) {
            ((ImmobilierCredit) credit).setType(PropertyType.valueOf(creditDTO.getPropertyType()));
        } else if (credit instanceof ProfessionnelCredit && "PROFESSIONNEL".equals(creditDTO.getType())) {
            ((ProfessionnelCredit) credit).setMotif(creditDTO.getMotif());
            ((ProfessionnelCredit) credit).setCompanyName(creditDTO.getCompanyName());
        } else {
            throw new RuntimeException("Incompatibilité de type pour la mise à jour du crédit");
        }
        credit = creditRepository.save(credit);
        return CreditMapper.toDTO(credit);
    }

    @Override
    public void delete(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> findByClientId(Long clientId) {
        return creditRepository.findByClientId(clientId).stream()
                .map(CreditMapper::toDTO)
                .collect(Collectors.toList());
    }
}