package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.dto.NewsFeedDTO;
import java.util.List;

public interface NewsFeedServiceInterface {

    List<NewsFeedDTO> getAllPost();
    void createPost(NewsFeedDTO newsFeedPost);
    void deletePost(Long idPost);
}
