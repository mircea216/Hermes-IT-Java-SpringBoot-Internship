package ro.societateahermes.backendservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Edition {
    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @JsonManagedReference
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EditionImage> images;

    @JsonManagedReference
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EditionVideo> videos;

    public Edition() {
        images = new ArrayList<>();
        videos = new ArrayList<>();
    }

    public void addImage(EditionImage image) {
        images.add(image);
    }

    public void removeImage(EditionImage image) {
        images.remove(image);
    }

    public void addVideo(EditionVideo video) {
        videos.add(video);
    }

    public void removeVideo(EditionVideo video) {
        videos.remove(video);
    }
}
