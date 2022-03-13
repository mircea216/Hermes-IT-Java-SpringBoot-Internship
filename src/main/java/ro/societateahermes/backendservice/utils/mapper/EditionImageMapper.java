package ro.societateahermes.backendservice.utils.mapper;

import ro.societateahermes.backendservice.entities.EditionImage;
import ro.societateahermes.backendservice.entities.dto.EditionImageDto;

public class EditionImageMapper {
    public static EditionImageDto convertToDto(EditionImage editionImage) {
        return new EditionImageDto(editionImage.getId(), editionImage.getPath());
    }

    public static EditionImage convertToEditionImage(EditionImageDto editionImageDto) {
        return new EditionImage(editionImageDto.getId(), editionImageDto.getPath());
    }
}
