package ma.enset.oussama.bouhouch.creditmanagementbackend.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class RemboursementDTO {
    private Long id;
    private Date date;
    private double amount;
    private String type; // Représentation sous forme de String de l'enum RemboursementType
    private Long creditId;
}