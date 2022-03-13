package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.societateahermes.backendservice.entities.Event;

import java.util.List;

@Data
@AllArgsConstructor
public class EditionDto {
    private Long id;

    private Event event;

    private List<EditionImageDto> images;

    private List<EditionVideoDto> videos;
}
