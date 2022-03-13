package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Sponsor;
import ro.societateahermes.backendservice.entities.dto.SponsorDTO;


public interface SponsorRepositoryInterface extends JpaRepository<Sponsor, Long> {

}
