package ro.societateahermes.backendservice.utils.mapper;

import ro.societateahermes.backendservice.entities.Edition;
import ro.societateahermes.backendservice.entities.EditionImage;
import ro.societateahermes.backendservice.entities.EditionVideo;
import ro.societateahermes.backendservice.entities.dto.EditionDto;
import ro.societateahermes.backendservice.entities.dto.EditionImageDto;
import ro.societateahermes.backendservice.entities.dto.EditionVideoDto;

import java.util.List;
import java.util.stream.Collectors;

public class EditionMapper {
    public static EditionDto convertToDto(Edition edition) {
        List<EditionImageDto> editionImagesDto = edition.getImages().stream()
                .map(EditionImageMapper::convertToDto)
                .collect(Collectors.toList());
        List<EditionVideoDto> editionVideosDto = edition.getVideos().stream()
                .map(EditionVideoMapper::convertToDto)
                .collect(Collectors.toList());

        return new EditionDto(edition.getId(), edition.getEvent(), editionImagesDto, editionVideosDto);
    }

    public static Edition convertToEdition(EditionDto editionDto) {
        List<EditionImage> editionImages = editionDto.getImages().stream()
                .map(EditionImageMapper::convertToEditionImage)
                .collect(Collectors.toList());
        List<EditionVideo> editionVideos = editionDto.getVideos().stream()
                .map(EditionVideoMapper::convertToEditionVideo)
                .collect(Collectors.toList());

        return new Edition(editionDto.getId(), editionDto.getEvent(), editionImages, editionVideos);
    }
}
