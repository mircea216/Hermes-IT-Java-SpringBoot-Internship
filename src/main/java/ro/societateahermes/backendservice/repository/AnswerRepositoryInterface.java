package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.form.Answer;

public interface AnswerRepositoryInterface extends JpaRepository<Answer,Long> {

}
