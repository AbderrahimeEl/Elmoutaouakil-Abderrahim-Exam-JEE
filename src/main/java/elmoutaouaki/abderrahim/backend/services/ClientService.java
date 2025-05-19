package elmoutaouaki.abderrahim.backend.services;

import elmoutaouaki.abderrahim.backend.dtos.ClientDTO;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDTO> findAll();
    Optional<ClientDTO> findById(Long id);
    ClientDTO save(ClientDTO clientDTO);
    void deleteById(Long id);
}
