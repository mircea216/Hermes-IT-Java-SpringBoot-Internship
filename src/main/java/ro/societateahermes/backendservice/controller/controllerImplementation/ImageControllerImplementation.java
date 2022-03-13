package ro.societateahermes.backendservice.controller.controllerImplementation;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;


import org.springframework.http.MediaType;
import ro.societateahermes.backendservice.entities.ImageType;
import ro.societateahermes.backendservice.exceptions.ImageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.societateahermes.backendservice.controller.ImageControllerInterface;
import ro.societateahermes.backendservice.entities.Image;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;
import ro.societateahermes.backendservice.service.serviceImplementation.ImageServiceImplementation;
import ro.societateahermes.backendservice.utils.PermissionChecker;
import ro.societateahermes.backendservice.utils.RolesActiveUser;


@RestController
@RequestMapping("/images")
public class ImageControllerImplementation implements ImageControllerInterface {

    private final ImageServiceImplementation imageService;

    public ImageControllerImplementation(ImageServiceImplementation imageService) {
        this.imageService = imageService;
    }

    @Override
    @PostMapping("/cd")
    public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException, UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.checkAdmin(roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        return imageService.convertMultiPartToFile(multipartFile, ImageType.CD);
    }

    @Override
    @PostMapping("/{eventId}")
    public String saveImageEvent(@PathVariable("eventId") long eventId, @RequestParam("file") MultipartFile multipartFile) throws IOException, UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(eventId, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        return imageService.convertMultiPartToFile(multipartFile, ImageType.EVENTS);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, value = "/cd")
    public ResponseEntity<?> getImageByPath(@RequestParam("name") String imageName) throws URISyntaxException, IOException {
        File image = imageService.getImageByPath(imageName, ImageType.CD);
        byte[] imageContent = Files.readAllBytes(image.toPath());
        return new ResponseEntity<>(imageContent, HttpStatus.OK);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, value = "/events")
    public ResponseEntity<?> getImageByPathForEvent(@RequestParam("name") String imageName) throws URISyntaxException, IOException {
        File image = imageService.getImageByPath(imageName, ImageType.EVENTS);
        byte[] imageContent = Files.readAllBytes(image.toPath());
        return new ResponseEntity<>(imageContent, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/cd")
    public void deleteImage(@RequestParam("name") String imageName) throws IOException, URISyntaxException, UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.checkAdmin(roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        imageService.deleteImage(imageName, ImageType.CD);
    }
}
