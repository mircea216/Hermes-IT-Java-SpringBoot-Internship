package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Image;

public interface ImageRepositoryInterface extends JpaRepository<Image, Long> {
}
