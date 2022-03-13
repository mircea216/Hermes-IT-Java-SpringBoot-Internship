package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.form.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
