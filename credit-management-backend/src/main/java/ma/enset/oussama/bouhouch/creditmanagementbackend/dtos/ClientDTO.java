package ma.enset.oussama.bouhouch.creditmanagementbackend.dtos;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String nom;
    private String email;
}