package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Edition;

public interface EditionRepository extends JpaRepository<Edition, Long> {
}
