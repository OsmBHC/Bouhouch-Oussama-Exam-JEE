package ma.enset.oussama.bouhouch.creditmanagementbackend.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CreditDTO {
    private Long id;
    private Date requestDate;
    private String status; // Représentation sous forme de String de l'enum CreditStatus
    private Date acceptationDate;
    private double amount;
    private Integer duration;
    private double interestRate;
    private Long clientId;
    private String type; // "PERSONAL", "IMMOBILIER", "PROFESSIONNEL"
    private String motif; // Pour PERSONAL et PROFESSIONNEL
    private String propertyType; // Pour IMMOBILIER
    private String companyName; // Pour PROFESSIONNEL
}