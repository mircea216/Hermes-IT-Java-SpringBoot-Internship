package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.EditionVideo;

public interface VideoRepository extends JpaRepository<EditionVideo, Long> {
}
