package ro.societateahermes.backendservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ImageControllerInterface {
    String saveImage(MultipartFile multipartFile) throws IOException, UnathorizeException;

    String saveImageEvent(long eventId, MultipartFile multipartFile) throws IOException, UnathorizeException;

    ResponseEntity<?> getImageByPath(String canonicalImagePath) throws URISyntaxException, IOException;

    ResponseEntity<?> getImageByPathForEvent(String imageName) throws URISyntaxException, IOException;

    void deleteImage(String canonicalImagePath) throws IOException, URISyntaxException, UnathorizeException;

}
