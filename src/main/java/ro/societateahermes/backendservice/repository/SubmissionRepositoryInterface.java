package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.form.Submission;


public interface SubmissionRepositoryInterface extends JpaRepository<Submission,Long> {
}
