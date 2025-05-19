package elmoutaouaki.abderrahim.backend.repositories;

import elmoutaouaki.abderrahim.backend.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
}