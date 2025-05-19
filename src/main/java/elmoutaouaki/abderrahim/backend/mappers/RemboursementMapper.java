package elmoutaouaki.abderrahim.backend.mappers;

import elmoutaouaki.abderrahim.backend.dtos.RemboursementDTO;
import elmoutaouaki.abderrahim.backend.entities.Remboursement;
import elmoutaouaki.abderrahim.backend.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemboursementMapper {

    private final CreditRepository creditRepository;

    @Autowired
    public RemboursementMapper(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public RemboursementDTO toDto(Remboursement remboursement) {
        if (remboursement == null) {
            return null;
        }
        
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        dto.setCreditId(remboursement.getCredit() != null ? remboursement.getCredit().getId() : null);
        
        return dto;
    }
    
    public Remboursement toEntity(RemboursementDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());
        
        if (dto.getCreditId() != null) {
            creditRepository.findById(dto.getCreditId()).ifPresent(remboursement::setCredit);
        }
        
        return remboursement;
    }
}