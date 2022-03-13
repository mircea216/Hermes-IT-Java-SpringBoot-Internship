package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Activity;

public interface ActivityRepositoryInterface extends JpaRepository<Activity, Long> {
}
