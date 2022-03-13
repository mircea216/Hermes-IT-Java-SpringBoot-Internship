package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Offer;

public interface OfferRepositoryInterface extends JpaRepository<Offer, Long> {
}
