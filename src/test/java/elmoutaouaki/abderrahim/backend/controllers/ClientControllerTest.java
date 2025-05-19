package elmoutaouaki.abderrahim.backend.controllers;

import elmoutaouaki.abderrahim.backend.dtos.ClientDTO;
import elmoutaouaki.abderrahim.backend.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setNom("Test Client");
        clientDTO.setEmail("test@example.com");
    }

    @Test
    void getAllClients() {
        // Arrange
        List<ClientDTO> clients = Arrays.asList(clientDTO);
        when(clientService.findAll()).thenReturn(clients);

        // Act
        ResponseEntity<List<ClientDTO>> response = clientController.getAllClients();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(clientService, times(1)).findAll();
    }

    @Test
    void getClientById() {
        // Arrange
        when(clientService.findById(1L)).thenReturn(Optional.of(clientDTO));

        // Act
        ResponseEntity<ClientDTO> response = clientController.getClientById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Client", response.getBody().getNom());
        verify(clientService, times(1)).findById(1L);
    }

    @Test
    void getClientById_NotFound() {
        // Arrange
        when(clientService.findById(2L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ClientDTO> response = clientController.getClientById(2L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clientService, times(1)).findById(2L);
    }

    @Test
    void createClient() {
        // Arrange
        when(clientService.save(any(ClientDTO.class))).thenReturn(clientDTO);

        // Act
        ResponseEntity<ClientDTO> response = clientController.createClient(clientDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Client", response.getBody().getNom());
        verify(clientService, times(1)).save(any(ClientDTO.class));
    }

    @Test
    void updateClient() {
        // Arrange
        when(clientService.findById(1L)).thenReturn(Optional.of(clientDTO));
        when(clientService.save(any(ClientDTO.class))).thenReturn(clientDTO);

        // Act
        ResponseEntity<ClientDTO> response = clientController.updateClient(1L, clientDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Client", response.getBody().getNom());
        verify(clientService, times(1)).findById(1L);
        verify(clientService, times(1)).save(any(ClientDTO.class));
    }

    @Test
    void updateClient_NotFound() {
        // Arrange
        when(clientService.findById(2L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ClientDTO> response = clientController.updateClient(2L, clientDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clientService, times(1)).findById(2L);
        verify(clientService, never()).save(any(ClientDTO.class));
    }

    @Test
    void deleteClient() {
        // Arrange
        when(clientService.findById(1L)).thenReturn(Optional.of(clientDTO));
        doNothing().when(clientService).deleteById(1L);

        // Act
        ResponseEntity<Void> response = clientController.deleteClient(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clientService, times(1)).findById(1L);
        verify(clientService, times(1)).deleteById(1L);
    }

    @Test
    void deleteClient_NotFound() {
        // Arrange
        when(clientService.findById(2L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = clientController.deleteClient(2L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clientService, times(1)).findById(2L);
        verify(clientService, never()).deleteById(anyLong());
    }
}