package ro.societateahermes.backendservice.utils.mapper;

import org.springframework.stereotype.Component;
import ro.societateahermes.backendservice.entities.NewsFeedPost;
import ro.societateahermes.backendservice.entities.dto.NewsFeedDTO;

@Component
public class NewsFeedMapper {

    public NewsFeedDTO convertToDTO(NewsFeedPost newsFeedPost){
        NewsFeedDTO newsFeedDTO = new NewsFeedDTO();
        newsFeedDTO.setPostId(newsFeedPost.getPostId());
        newsFeedDTO.setPostDescription(newsFeedPost.getPostDescription());
        newsFeedDTO.setPostImagePath(newsFeedPost.getPostImagePath());
        newsFeedDTO.setPostLink(newsFeedPost.getPostLink());

        return newsFeedDTO;
    }

    public NewsFeedPost convertToEntity(NewsFeedDTO newsFeedDTO){
        NewsFeedPost newsFeedPost = new NewsFeedPost();
        newsFeedPost.setPostDescription(newsFeedDTO.getPostDescription());
        newsFeedPost.setPostImagePath(newsFeedDTO.getPostImagePath());
        newsFeedPost.setPostLink(newsFeedDTO.getPostLink());

        return newsFeedPost;
    }
}
