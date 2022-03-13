package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.societateahermes.backendservice.entities.Edition;
import ro.societateahermes.backendservice.entities.EditionImage;
import ro.societateahermes.backendservice.entities.EditionVideo;
import ro.societateahermes.backendservice.entities.ImageType;
import ro.societateahermes.backendservice.entities.dto.EditionDto;
import ro.societateahermes.backendservice.repository.EditionImageRepository;
import ro.societateahermes.backendservice.repository.EditionRepository;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;
import ro.societateahermes.backendservice.repository.VideoRepository;
import ro.societateahermes.backendservice.service.EditionServiceInterface;
import ro.societateahermes.backendservice.service.ImageServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.EditionMapper;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditionService implements EditionServiceInterface {
    private final EventRepositoryInterface eventRepository;
    private final EditionRepository editionRepository;
    private final EditionImageRepository imageRepository;
    private final VideoRepository videoRepository;
    private final ImageServiceInterface imageService;

    public EditionService(EventRepositoryInterface eventRepository, EditionRepository editionRepository, EditionImageRepository imageRepository, VideoRepository videoRepository, ImageServiceInterface imageService) {
        this.eventRepository = eventRepository;
        this.editionRepository = editionRepository;
        this.imageRepository = imageRepository;
        this.videoRepository = videoRepository;
        this.imageService = imageService;
    }

    public Edition getEditionById(Long editionId) {
        return editionRepository.findById(editionId).orElseThrow();
    }

    public List<EditionDto> getAll() {
        List<Edition> editions = editionRepository.findAll();
        return editions.stream()
                .map(EditionMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public EditionImage getImageById(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow();
    }

    public Edition createEmptyEdition(Long eventId) {
        Edition edition = new Edition();
        edition.setEvent(eventRepository.findById(eventId).orElseThrow());
        return editionRepository.save(edition);
    }

    @Transactional
    public void deleteEdition(Long editionId) throws IOException {
        this.deleteImagesOfEdition(editionId);
        this.deleteVideosOfEdition(editionId);
        editionRepository.delete(editionRepository.findById(editionId).orElseThrow());
    }

    @Transactional
    public void saveMediaToEdition(Long editionId, List<MultipartFile> multipartImages, List<MultipartFile> multipartVideos) {
        this.saveImagesToEdition(editionId, multipartImages);
        this.saveVideosToEdition(editionId, multipartVideos);
    }

    public void saveImagesToEdition(Long editionId, List<MultipartFile> multipartImages) {
        Edition edition = editionRepository.findById(editionId).orElseThrow();

        try {
            for (MultipartFile multipartImage : multipartImages) {
                String imagePath = imageService.convertMultiPartToFile(multipartImage, ImageType.EDITION);

                EditionImage image = new EditionImage(edition, imagePath);
                imageRepository.save(image);
                edition.addImage(image);
                editionRepository.save(edition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveVideosToEdition(Long editionId, List<MultipartFile> multipartVideos) {
        Edition edition = editionRepository.findById(editionId).orElseThrow();

        try {
            for (MultipartFile multipartVideo : multipartVideos) {
                String videoPath = imageService.convertMultiPartToFile(multipartVideo, ImageType.EDITION);

                edition.addVideo(new EditionVideo(edition, videoPath));
                editionRepository.save(edition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteMediaFromEdition(Long editionId, List<Long> imageIds, List<Long> videosIds) throws IOException {
        this.deleteImagesFromEdition(editionId, imageIds);
        this.deleteVideosFromEdition(editionId, videosIds);
    }

    public void deleteImagesFromEdition(Long editionId, List<Long> imageIds) throws IOException {
        Edition edition = editionRepository.findById(editionId).orElseThrow();

        for (Long imageId : imageIds) {
            EditionImage image = imageRepository.findById(imageId).orElseThrow();

            imageService.deleteImage(image.getPath());

            edition.removeImage(image);
            editionRepository.save(edition);
        }
    }

    public void deleteImagesOfEdition(Long editionId) throws IOException {
        Edition edition = editionRepository.findById(editionId).orElseThrow();

        List<EditionImage> images = edition.getImages();
        for (int i = 0; i < images.size(); i++) {
            EditionImage image = images.get(i);

            imageService.deleteImage(image.getPath());

            edition.removeImage(image);
            editionRepository.save(edition);
            i--;
        }
    }

    public void deleteVideosFromEdition(Long editionId, List<Long> videosIds) throws IOException {
        Edition edition = editionRepository.findById(editionId).orElseThrow();

        for (Long videoId : videosIds) {
            EditionVideo video = videoRepository.findById(videoId).orElseThrow();

            imageService.deleteImage(video.getPath());

            edition.removeVideo(video);
            editionRepository.save(edition);
        }
    }

    public void deleteVideosOfEdition(Long editionId) throws IOException {
        Edition edition = editionRepository.findById(editionId).orElseThrow();

        List<EditionVideo> videos = edition.getVideos();
        for (int i = 0; i < videos.size(); i++) {
            EditionVideo video = videos.get(i);

            imageService.deleteImage(video.getPath());

            edition.removeVideo(video);
            editionRepository.save(edition);
            i--;
        }
    }
}
