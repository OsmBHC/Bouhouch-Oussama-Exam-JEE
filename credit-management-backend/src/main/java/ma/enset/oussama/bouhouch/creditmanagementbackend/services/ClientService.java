package ma.enset.oussama.bouhouch.creditmanagementbackend.services;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.ClientDTO;
import java.util.List;

public interface ClientService {
    List<ClientDTO> findAll();
    ClientDTO findById(Long id);
    ClientDTO create(ClientDTO clientDTO);
    ClientDTO update(Long id, ClientDTO clientDTO);
    void delete(Long id);
}