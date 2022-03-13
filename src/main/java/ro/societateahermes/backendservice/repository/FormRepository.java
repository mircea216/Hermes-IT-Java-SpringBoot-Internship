package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.form.Form;

public interface FormRepository extends JpaRepository<Form, Long> {
}
