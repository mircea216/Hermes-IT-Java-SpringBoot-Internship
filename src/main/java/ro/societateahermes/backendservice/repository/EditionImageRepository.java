package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.EditionImage;

public interface EditionImageRepository extends JpaRepository<EditionImage, Long> {
}
