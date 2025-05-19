package elmoutaouaki.abderrahim.backend.mappers;

import elmoutaouaki.abderrahim.backend.dtos.CreditDTO;
import elmoutaouaki.abderrahim.backend.dtos.CreditImmobilierDTO;
import elmoutaouaki.abderrahim.backend.dtos.CreditPersonnelDTO;
import elmoutaouaki.abderrahim.backend.dtos.CreditProfessionnelDTO;
import elmoutaouaki.abderrahim.backend.entities.Credit;
import elmoutaouaki.abderrahim.backend.entities.CreditImmobilier;
import elmoutaouaki.abderrahim.backend.entities.CreditPersonnel;
import elmoutaouaki.abderrahim.backend.entities.CreditProfessionnel;
import elmoutaouaki.abderrahim.backend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    private final ClientRepository clientRepository;

    @Autowired
    public CreditMapper(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public CreditDTO toDto(Credit credit) {
        if (credit == null) {
            return null;
        }

        CreditDTO dto;
        
        if (credit instanceof CreditImmobilier) {
            CreditImmobilier creditImmobilier = (CreditImmobilier) credit;
            CreditImmobilierDTO creditImmobilierDTO = new CreditImmobilierDTO();
            creditImmobilierDTO.setTypeBien(creditImmobilier.getTypeBien());
            dto = creditImmobilierDTO;
            dto.setType("IMMOBILIER");
        } else if (credit instanceof CreditPersonnel) {
            CreditPersonnel creditPersonnel = (CreditPersonnel) credit;
            CreditPersonnelDTO creditPersonnelDTO = new CreditPersonnelDTO();
            creditPersonnelDTO.setMotif(creditPersonnel.getMotif());
            dto = creditPersonnelDTO;
            dto.setType("PERSONNEL");
        } else if (credit instanceof CreditProfessionnel) {
            CreditProfessionnel creditProfessionnel = (CreditProfessionnel) credit;
            CreditProfessionnelDTO creditProfessionnelDTO = new CreditProfessionnelDTO();
            creditProfessionnelDTO.setMotif(creditProfessionnel.getMotif());
            creditProfessionnelDTO.setRaisonSocialeEntreprise(creditProfessionnel.getRaisonSocialeEntreprise());
            dto = creditProfessionnelDTO;
            dto.setType("PROFESSIONNEL");
        } else {
            dto = new CreditDTO();
        }
        
        dto.setId(credit.getId());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut());
        dto.setDateAcceptation(credit.getDateAcceptation());
        dto.setMontant(credit.getMontant());
        dto.setDureeRemboursement(credit.getDureeRemboursement());
        dto.setTauxInteret(credit.getTauxInteret());
        dto.setClientId(credit.getClient() != null ? credit.getClient().getId() : null);
        
        return dto;
    }
    
    public Credit toEntity(CreditDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Credit credit;
        
        if (dto instanceof CreditImmobilierDTO) {
            CreditImmobilierDTO creditImmobilierDTO = (CreditImmobilierDTO) dto;
            CreditImmobilier creditImmobilier = new CreditImmobilier();
            creditImmobilier.setTypeBien(creditImmobilierDTO.getTypeBien());
            credit = creditImmobilier;
        } else if (dto instanceof CreditPersonnelDTO) {
            CreditPersonnelDTO creditPersonnelDTO = (CreditPersonnelDTO) dto;
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            creditPersonnel.setMotif(creditPersonnelDTO.getMotif());
            credit = creditPersonnel;
        } else if (dto instanceof CreditProfessionnelDTO) {
            CreditProfessionnelDTO creditProfessionnelDTO = (CreditProfessionnelDTO) dto;
            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            creditProfessionnel.setMotif(creditProfessionnelDTO.getMotif());
            creditProfessionnel.setRaisonSocialeEntreprise(creditProfessionnelDTO.getRaisonSocialeEntreprise());
            credit = creditProfessionnel;
        } else {
            throw new IllegalArgumentException("Unknown credit type");
        }
        
        credit.setId(dto.getId());
        credit.setDateDemande(dto.getDateDemande());
        credit.setStatut(dto.getStatut());
        credit.setDateAcceptation(dto.getDateAcceptation());
        credit.setMontant(dto.getMontant());
        credit.setDureeRemboursement(dto.getDureeRemboursement());
        credit.setTauxInteret(dto.getTauxInteret());
        
        if (dto.getClientId() != null) {
            clientRepository.findById(dto.getClientId()).ifPresent(credit::setClient);
        }
        
        return credit;
    }
}