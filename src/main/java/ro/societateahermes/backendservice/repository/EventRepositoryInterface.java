package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Event;


import java.util.Optional;

public interface EventRepositoryInterface extends JpaRepository<Event, Long> {

    Optional<Event> findByEventName(String eventName);

}
