package elmoutaouaki.abderrahim.backend.repositories;

import elmoutaouaki.abderrahim.backend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}