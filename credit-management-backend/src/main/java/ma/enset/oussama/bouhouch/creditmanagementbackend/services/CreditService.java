package ma.enset.oussama.bouhouch.creditmanagementbackend.services;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.CreditDTO;
import java.util.List;

public interface CreditService {
    List<CreditDTO> findAll();
    CreditDTO findById(Long id);
    CreditDTO create(CreditDTO creditDTO);
    CreditDTO update(Long id, CreditDTO creditDTO);
    void delete(Long id);
    List<CreditDTO> findByClientId(Long clientId);
}