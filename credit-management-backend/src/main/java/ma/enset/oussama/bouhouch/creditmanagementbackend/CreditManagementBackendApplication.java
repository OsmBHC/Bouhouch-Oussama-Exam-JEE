package ma.enset.oussama.bouhouch.creditmanagementbackend;

import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Client;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.PersonalCredit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.ImmobilierCredit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.ProfessionnelCredit;
import ma.enset.oussama.bouhouch.creditmanagementbackend.entities.Remboursement;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.CreditStatus;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.RemboursementType;
import ma.enset.oussama.bouhouch.creditmanagementbackend.enums.PropertyType;
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
            // 1. Create and save three Clients
            Client client1 = new Client();
            client1.setNom("Oussama Bouhouch");
            client1.setEmail("oussama@example.com");
            client1 = clientRepository.save(client1);
            System.out.println("Client 1 saved: " + client1);

            Client client2 = new Client();
            client2.setNom("Amina El Fassi");
            client2.setEmail("amina@example.com");
            client2 = clientRepository.save(client2);
            System.out.println("Client 2 saved: " + client2);

            Client client3 = new Client();
            client3.setNom("Youssef Amrani");
            client3.setEmail("youssef@example.com");
            client3 = clientRepository.save(client3);
            System.out.println("Client 3 saved: " + client3);

            // 2. Create and save multiple Credits for the Clients
            // PersonalCredit for Client 1
            PersonalCredit personalCredit = new PersonalCredit();
            personalCredit.setRequestDate(new Date());
            personalCredit.setStatus(CreditStatus.EN_COURS);
            personalCredit.setAmount(5000.0);
            personalCredit.setDuration(24);
            personalCredit.setInterestRate(5.5);
            personalCredit.setMotif("Achat de voiture");
            personalCredit.setClient(client1);
            personalCredit = creditRepository.save(personalCredit);
            System.out.println("PersonalCredit saved: " + personalCredit);

            // ImmobilierCredit for Client 2
            ImmobilierCredit immobilierCredit = new ImmobilierCredit();
            immobilierCredit.setRequestDate(new Date());
            immobilierCredit.setStatus(CreditStatus.ACCEPTE);
            immobilierCredit.setAcceptationDate(new Date());
            immobilierCredit.setAmount(200000.0);
            immobilierCredit.setDuration(180);
            immobilierCredit.setInterestRate(3.2);
            immobilierCredit.setType(PropertyType.APPARTEMENT);
            immobilierCredit.setClient(client2);
            immobilierCredit = creditRepository.save(immobilierCredit);
            System.out.println("ImmobilierCredit saved: " + immobilierCredit);

            // ProfessionnelCredit for Client 3
            ProfessionnelCredit professionnelCredit = new ProfessionnelCredit();
            professionnelCredit.setRequestDate(new Date());
            professionnelCredit.setStatus(CreditStatus.REJETE);
            professionnelCredit.setAmount(15000.0);
            professionnelCredit.setDuration(36);
            professionnelCredit.setInterestRate(6.0);
            professionnelCredit.setMotif("Expansion d'entreprise");
            professionnelCredit.setCompanyName("Amrani Tech");
            professionnelCredit.setClient(client3);
            professionnelCredit = creditRepository.save(professionnelCredit);
            System.out.println("ProfessionnelCredit saved: " + professionnelCredit);

            // Another PersonalCredit for Client 2
            PersonalCredit personalCredit2 = new PersonalCredit();
            personalCredit2.setRequestDate(new Date());
            personalCredit2.setStatus(CreditStatus.EN_COURS);
            personalCredit2.setAmount(3000.0);
            personalCredit2.setDuration(12);
            personalCredit2.setInterestRate(4.8);
            personalCredit2.setMotif("Études");
            personalCredit2.setClient(client2);
            personalCredit2 = creditRepository.save(personalCredit2);
            System.out.println("PersonalCredit 2 saved: " + personalCredit2);

            // 3. Create and save Remboursements for some Credits
            Remboursement remboursement1 = new Remboursement();
            remboursement1.setDate(new Date());
            remboursement1.setAmount(250.0);
            remboursement1.setType(RemboursementType.MENSUALITE);
            remboursement1.setCredit(personalCredit);
            remboursement1 = remboursementRepository.save(remboursement1);
            System.out.println("Remboursement 1 saved: " + remboursement1);

            Remboursement remboursement2 = new Remboursement();
            remboursement2.setDate(new Date());
            remboursement2.setAmount(1500.0);
            remboursement2.setType(RemboursementType.ANTICIPE);
            remboursement2.setCredit(immobilierCredit);
            remboursement2 = remboursementRepository.save(remboursement2);
            System.out.println("Remboursement 2 saved: " + remboursement2);

            // 4. Fetch and verify data
            System.out.println("All Clients: " + clientRepository.findAll());
            System.out.println("All Credits: " + creditRepository.findAll());
            System.out.println("All Remboursements: " + remboursementRepository.findAll());
            System.out.println("Credits for Client ID " + client1.getId() + ": " + creditRepository.findByClientId(client1.getId()));
            System.out.println("Credits for Client ID " + client2.getId() + ": " + creditRepository.findByClientId(client2.getId()));
            System.out.println("Credits for Client ID " + client3.getId() + ": " + creditRepository.findByClientId(client3.getId()));
            System.out.println("Remboursements for Credit ID " + personalCredit.getId() + ": " + remboursementRepository.findByCreditId(personalCredit.getId()));
            System.out.println("Remboursements for Credit ID " + immobilierCredit.getId() + ": " + remboursementRepository.findByCreditId(immobilierCredit.getId()));
        };
    }
}