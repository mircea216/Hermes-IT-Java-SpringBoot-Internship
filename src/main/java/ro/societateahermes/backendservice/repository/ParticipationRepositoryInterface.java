package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Event;
import org.springframework.stereotype.Repository;
import ro.societateahermes.backendservice.entities.Participation;

import java.util.List;


@Repository
public interface ParticipationRepositoryInterface extends JpaRepository<Participation, Long> {
    List<Participation> findParticipationByEvent(Event event);
    Participation findByUserID(Long userId);
}
