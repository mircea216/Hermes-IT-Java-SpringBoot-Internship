package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.User;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {
}
