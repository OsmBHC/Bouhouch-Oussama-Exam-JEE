package ma.enset.oussama.bouhouch.creditmanagementbackend;

import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Client;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.PersonalCredit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Remboursement;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.CreditStatus;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.RemboursementType;
import ma.enset.oussama.bouhouch.creditmanagementbackend.repositories.ClientRepository;
import ma.enset.oussama.bouhouch.creditmanagementbackend.repositories.CreditRepository;
import ma.enset.oussama.bouhouch.creditmanagementbackend.repositories.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootApplication
public class CreditManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditManagementBackendApplication.class, args);
    }

    @Bean
    @Transactional // Ensures the session remains open during the entire operation
    public CommandLineRunner testDaoLayer(ClientRepository clientRepository, CreditRepository creditRepository, RemboursementRepository remboursementRepository) {
        return args -> {
            // 1. Create and save a Client
            Client client = new Client();
            client.setNom("Oussama Bouhouch");
            client.setEmail("oussama@example.com");
            client = clientRepository.save(client);
            System.out.println("Client saved: " + client);

            // 2. Create and save a PersonalCredit for the Client
            PersonalCredit personalCredit = new PersonalCredit();
            personalCredit.setRequestDate(new Date());
            personalCredit.setStatus(CreditStatus.EN_COURS);
            personalCredit.setAmount(5000.0);
            personalCredit.setDuration(24);
            personalCredit.setInterestRate(5.5);
            personalCredit.setMotif("Achat de voiture");
            personalCredit.setClient(client);
            personalCredit = creditRepository.save(personalCredit);
            System.out.println("PersonalCredit saved: " + personalCredit);

            // 3. Create and save a Remboursement for the Credit
            Remboursement remboursement = new Remboursement();
            remboursement.setDate(new Date());
            remboursement.setAmount(250.0);
            remboursement.setType(RemboursementType.MENSUALITE);
            remboursement.setCredit(personalCredit);
            remboursement = remboursementRepository.save(remboursement);
            System.out.println("Remboursement saved: " + remboursement);

            // 4. Fetch and verify data
            System.out.println("All Clients: " + clientRepository.findAll());
            System.out.println("All Credits: " + creditRepository.findAll());
            System.out.println("All Remboursements: " + remboursementRepository.findAll());
            System.out.println("Credits for Client ID " + client.getId() + ": " + creditRepository.findByClientId(client.getId()));
            System.out.println("Remboursements for Credit ID " + personalCredit.getId() + ": " + remboursementRepository.findByCreditId(personalCredit.getId()));
        };
    }
}