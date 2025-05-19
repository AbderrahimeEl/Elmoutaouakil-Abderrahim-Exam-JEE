package elmoutaouaki.abderrahim.backend.mappers;

import elmoutaouaki.abderrahim.backend.dtos.ClientDTO;
import elmoutaouaki.abderrahim.backend.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDTO toDto(Client client) {
        if (client == null) {
            return null;
        }
        
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        
        return dto;
    }
    
    public Client toEntity(ClientDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        
        return client;
    }
}