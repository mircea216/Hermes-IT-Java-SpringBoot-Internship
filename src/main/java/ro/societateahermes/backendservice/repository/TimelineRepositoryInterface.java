package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Timeline;

public interface TimelineRepositoryInterface extends JpaRepository<Timeline, Long> {
}
