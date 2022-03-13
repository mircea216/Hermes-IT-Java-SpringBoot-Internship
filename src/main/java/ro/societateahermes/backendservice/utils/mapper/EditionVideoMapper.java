package ro.societateahermes.backendservice.utils.mapper;

import ro.societateahermes.backendservice.entities.EditionVideo;
import ro.societateahermes.backendservice.entities.dto.EditionVideoDto;

public class EditionVideoMapper {
    public static EditionVideoDto convertToDto(EditionVideo editionVideo) {
        return new EditionVideoDto(editionVideo.getId(), editionVideo.getPath());
    }

    public static EditionVideo convertToEditionVideo(EditionVideoDto editionImageDto) {
        return new EditionVideo(editionImageDto.getId(), editionImageDto.getPath());
    }
}
