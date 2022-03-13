package ro.societateahermes.backendservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.societateahermes.backendservice.entities.NewsFeedPost;

public interface NewsFeedRepositoryInterface extends JpaRepository<NewsFeedPost, Long> {
}
