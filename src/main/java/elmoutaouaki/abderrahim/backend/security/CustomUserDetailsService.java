package elmoutaouaki.abderrahim.backend.security;

import elmoutaouaki.abderrahim.backend.entities.Client;
import elmoutaouaki.abderrahim.backend.repositories.ClientRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public CustomUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Client not found with email: " + email));

        // Since Client doesn't have a password field, we're using a default password
        // This is a temporary solution and should be replaced with proper authentication
        return new org.springframework.security.core.userdetails.User(
                client.getEmail(),
                "$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG", // "password" encoded
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
