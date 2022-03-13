package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsFeedDTO {

    private long postId;
    private String postDescription;
    private String postImagePath;
    private String postLink;
}
