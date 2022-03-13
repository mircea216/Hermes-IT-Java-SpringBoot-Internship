package ro.societateahermes.backendservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class NewsFeedPost {
    @Id
    @GeneratedValue
    private long postId;
    private String postDescription;
    private String postImagePath;
    private String postLink;
}
