package elmoutaouaki.abderrahim.backend.services;

import elmoutaouaki.abderrahim.backend.dtos.RemboursementDTO;
import java.util.List;
import java.util.Optional;

public interface RemboursementService {
    List<RemboursementDTO> findAll();
    Optional<RemboursementDTO> findById(Long id);
    RemboursementDTO save(RemboursementDTO remboursementDTO);
    void deleteById(Long id);
    List<RemboursementDTO> findByCreditId(Long creditId);
}
