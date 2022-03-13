package ro.societateahermes.backendservice.service;

import org.springframework.web.multipart.MultipartFile;
import ro.societateahermes.backendservice.entities.Edition;
import ro.societateahermes.backendservice.entities.EditionImage;
import ro.societateahermes.backendservice.entities.dto.EditionDto;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface EditionServiceInterface {
    List<EditionDto> getAll();

    EditionImage getImageById(Long imageId);

    Edition getEditionById(Long editionId);

    Edition createEmptyEdition(Long eventId);

    void deleteEdition(Long editionId) throws IOException;

    void saveMediaToEdition(Long editionId, List<MultipartFile> multipartImages, List<MultipartFile> multipartVideos);

    void saveImagesToEdition(Long editionId, List<MultipartFile> multipartImages);

    void saveVideosToEdition(Long editionId, List<MultipartFile> multipartVideos);

    void deleteMediaFromEdition(Long editionId, List<Long> imageIds, List<Long> videosIds) throws IOException, URISyntaxException;

    void deleteImagesFromEdition(Long editionId, List<Long> imageIds) throws IOException, URISyntaxException;

    void deleteImagesOfEdition(Long editionId) throws IOException;

    void deleteVideosFromEdition(Long editionId, List<Long> videosIds) throws IOException;

    void deleteVideosOfEdition(Long editionId) throws IOException;
}
