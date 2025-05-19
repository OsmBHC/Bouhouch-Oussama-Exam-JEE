package ma.enset.oussama.bouhouch.creditmanagementbackend.services.impl;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.ClientDTO;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Client;
import ma.enset.oussama.bouhouch.creditmanagementbackend.mappers.ClientMapper;
import ma.enset.oussama.bouhouch.creditmanagementbackend.repositories.ClientRepository;
import ma.enset.oussama.bouhouch.creditmanagementbackend.services.ClientService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        return ClientMapper.toDTO(client);
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        Client client = ClientMapper.toEntity(clientDTO);
        client = clientRepository.save(client);
        return ClientMapper.toDTO(client);
    }

    @Override
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        client.setNom(clientDTO.getNom());
        client.setEmail(clientDTO.getEmail());
        client = clientRepository.save(client);
        return ClientMapper.toDTO(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}