package elmoutaouaki.abderrahim.backend.services;

import elmoutaouaki.abderrahim.backend.dtos.CreditDTO;
import java.util.List;
import java.util.Optional;

public interface CreditService {
    List<CreditDTO> findAll();
    Optional<CreditDTO> findById(Long id);
    CreditDTO save(CreditDTO creditDTO);
    void deleteById(Long id);
    List<CreditDTO> findByClientId(Long clientId);
}
