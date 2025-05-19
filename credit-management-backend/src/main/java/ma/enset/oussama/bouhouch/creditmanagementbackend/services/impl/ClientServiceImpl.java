package ma.enset.oussama.bouhouch.creditmanagementbackend.services.impl;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.ClientDTO;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Client;
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
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return toDTO(client);
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        Client client = toEntity(clientDTO);
        client = clientRepository.save(client);
        return toDTO(client);
    }

    @Override
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        client.setNom(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client = clientRepository.save(client);
        return toDTO(client);
    }

    @Override
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDTO> searchClients(String keyword) {
        return clientRepository.searchClients(keyword).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getNom());
        dto.setEmail(client.getEmail());
        return dto;
    }

    private Client toEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setNom(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        return client;
    }
}