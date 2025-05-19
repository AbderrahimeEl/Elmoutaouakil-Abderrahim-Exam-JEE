package elmoutaouaki.abderrahim.backend;

import elmoutaouaki.abderrahim.backend.entities.*;
import elmoutaouaki.abderrahim.backend.repositories.ClientRepository;
import elmoutaouaki.abderrahim.backend.repositories.CreditRepository;
import elmoutaouaki.abderrahim.backend.repositories.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ClientRepository clientRepository,
            CreditRepository creditRepository,
            RemboursementRepository remboursementRepository) {
        return args -> {
            // Create clients
            Client client1 = new Client();
            client1.setNom("abderrahim");
            client1.setEmail("elmoutaouakil@example.com");
            clientRepository.save(client1);

            Client client2 = new Client();
            client2.setNom("abdo");
            client2.setEmail("exam@example.com");
            clientRepository.save(client2);

            // Create personal credit
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            creditPersonnel.setClient(client1);
            creditPersonnel.setDateDemande(new Date());
            creditPersonnel.setStatut(Credit.StatutCredit.ACCEPTE);
            creditPersonnel.setDateAcceptation(new Date());
            creditPersonnel.setMontant(50000.0);
            creditPersonnel.setDureeRemboursement(24);
            creditPersonnel.setTauxInteret(4.5);
            creditPersonnel.setMotif("Achat voiture");
            creditRepository.save(creditPersonnel);

            // Create real estate credit
            CreditImmobilier creditImmobilier = new CreditImmobilier();
            creditImmobilier.setClient(client2);
            creditImmobilier.setDateDemande(new Date());
            creditImmobilier.setStatut(Credit.StatutCredit.ACCEPTE);
            creditImmobilier.setDateAcceptation(new Date());
            creditImmobilier.setMontant(200000.0);
            creditImmobilier.setDureeRemboursement(240);
            creditImmobilier.setTauxInteret(3.2);
            creditImmobilier.setTypeBien(CreditImmobilier.TypeBien.APPARTEMENT);
            creditRepository.save(creditImmobilier);

            // Create professional credit
            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            creditProfessionnel.setClient(client1);
            creditProfessionnel.setDateDemande(new Date());
            creditProfessionnel.setStatut(Credit.StatutCredit.EN_COURS);
            creditProfessionnel.setMontant(100000.0);
            creditProfessionnel.setDureeRemboursement(60);
            creditProfessionnel.setTauxInteret(5.0);
            creditProfessionnel.setMotif("Expansion d'entreprise");
            creditProfessionnel.setRaisonSocialeEntreprise("Doe Enterprises");
            creditRepository.save(creditProfessionnel);

            // Create repayments
            Remboursement remboursement1 = new Remboursement();
            remboursement1.setCredit(creditPersonnel);
            remboursement1.setDate(new Date());
            remboursement1.setMontant(2200.0);
            remboursement1.setType(Remboursement.TypeRemboursement.MENSUALITE);
            remboursementRepository.save(remboursement1);

            Remboursement remboursement2 = new Remboursement();
            remboursement2.setCredit(creditImmobilier);
            remboursement2.setDate(new Date());
            remboursement2.setMontant(950.0);
            remboursement2.setType(Remboursement.TypeRemboursement.MENSUALITE);
            remboursementRepository.save(remboursement2);

            System.out.println("Test data has been inserted successfully!");
        };
    }
}
