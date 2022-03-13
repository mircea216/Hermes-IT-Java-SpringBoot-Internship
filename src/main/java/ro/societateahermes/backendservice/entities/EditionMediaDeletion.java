package ro.societateahermes.backendservice.entities;

import lombok.Data;

import java.util.List;

@Data
public class EditionMediaDeletion {
    private List<Long> imagesIds;

    private List<Long> videosIds;
}
