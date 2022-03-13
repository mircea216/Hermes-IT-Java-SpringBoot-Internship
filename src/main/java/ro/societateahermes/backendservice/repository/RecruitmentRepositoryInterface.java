package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.Recruitment;

public interface RecruitmentRepositoryInterface extends JpaRepository<Recruitment,Long> {
}
