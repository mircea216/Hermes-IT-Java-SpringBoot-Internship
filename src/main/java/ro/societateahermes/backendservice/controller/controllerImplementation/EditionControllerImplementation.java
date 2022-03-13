package ro.societateahermes.backendservice.controller.controllerImplementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.societateahermes.backendservice.controller.EditionControllerInterface;
import ro.societateahermes.backendservice.entities.EditionImage;
import ro.societateahermes.backendservice.entities.EditionMediaDeletion;
import ro.societateahermes.backendservice.entities.EditionMediaUpload;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.dto.EditionDto;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;
import ro.societateahermes.backendservice.service.serviceImplementation.EditionService;
import ro.societateahermes.backendservice.utils.PermissionChecker;
import ro.societateahermes.backendservice.utils.RolesActiveUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/edition")
public class EditionControllerImplementation implements EditionControllerInterface {
    private final EditionService editionService;

    public EditionControllerImplementation(EditionService editionService) {
        this.editionService = editionService;
    }

    @GetMapping
    public List<EditionDto> getAll() {
        return editionService.getAll();
    }

    @PostMapping("/{eventId}")
    public void saveNewEditionWithMedia(@PathVariable Long eventId,
                                        @ModelAttribute EditionMediaUpload editionMediaUpload) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(eventId, roles)) {
            throw new UnathorizeException("User is not authorized");
        }

        List<MultipartFile> images = editionMediaUpload.getImages();
        List<MultipartFile> videos = editionMediaUpload.getVideos();

        Long newEditionId = editionService.createEmptyEdition(eventId).getId();
        if (images != null) {
            editionService.saveImagesToEdition(newEditionId, images);
        }
        if (videos != null) {
            editionService.saveVideosToEdition(newEditionId, videos);
        }
    }

    @DeleteMapping("/{editionId}")
    public void deleteEdition(@PathVariable("editionId") Long editionId) throws IOException, UnathorizeException {
        Event event = editionService.getEditionById(editionId).getEvent();
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(event.getId(), roles)) {
            throw new UnathorizeException("User is not authorized");
        }

        editionService.deleteEdition(editionId);
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImageById(@PathVariable("imageId") Long imageId) throws IOException {
        EditionImage image = editionService.getImageById(imageId);
        byte[] imageContent = Files.readAllBytes(Path.of(image.getPath()));
        return new ResponseEntity<>(imageContent, HttpStatus.OK);
    }

    @PostMapping("/{editionId}/media")
    public ResponseEntity<Object> addMediaToEdition(@PathVariable("editionId") Long editionId,
                                                    @ModelAttribute EditionMediaUpload editionMediaUpload) throws UnathorizeException {
        Event event = editionService.getEditionById(editionId).getEvent();
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(event.getId(), roles)) {
            throw new UnathorizeException("User is not authorized");
        }

        List<MultipartFile> images = editionMediaUpload.getImages();
        List<MultipartFile> videos = editionMediaUpload.getVideos();

        if (images == null && videos == null) {
            return new ResponseEntity<>("No image or video sent", HttpStatus.BAD_REQUEST);
        }

        if (images != null && videos != null) {
            editionService.saveMediaToEdition(editionId, images, videos);
        } else if (images != null) {
            editionService.saveImagesToEdition(editionId, images);
        } else {
            editionService.saveVideosToEdition(editionId, videos);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{editionId}/media")
    public ResponseEntity<Object> deleteMediaFromEdition(@PathVariable("editionId") Long editionId,
                                                         @RequestBody EditionMediaDeletion editionMediaDeletion) throws IOException, UnathorizeException {
        Event event = editionService.getEditionById(editionId).getEvent();
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(event.getId(), roles)) {
            throw new UnathorizeException("User is not authorized");
        }

        List<Long> imagesIds = editionMediaDeletion.getImagesIds();
        List<Long> videosIds = editionMediaDeletion.getVideosIds();

        if (imagesIds == null && videosIds == null) {
            return new ResponseEntity<>("No image or video sent", HttpStatus.BAD_REQUEST);
        }

        if (imagesIds != null && videosIds != null) {
            editionService.deleteMediaFromEdition(editionId, imagesIds, videosIds);
        } else if (imagesIds != null) {
            editionService.deleteImagesFromEdition(editionId, imagesIds);
        } else {
            editionService.deleteVideosFromEdition(editionId, videosIds);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
