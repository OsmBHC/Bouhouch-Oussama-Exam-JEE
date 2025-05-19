package ma.enset.oussama.bouhouch.creditmanagementbackend.mappers;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.ClientDTO;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Client;

public class ClientMapper {
    public static ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getNom());
        dto.setEmail(client.getEmail());
        return dto;
    }

    public static Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getName());
        client.setEmail(dto.getEmail());
        return client;
    }
}