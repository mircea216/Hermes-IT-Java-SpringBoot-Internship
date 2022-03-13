package ro.societateahermes.backendservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import ro.societateahermes.backendservice.entities.ImageType;
import ro.societateahermes.backendservice.exceptions.ImageException;
import org.springframework.web.multipart.MultipartFile;
import ro.societateahermes.backendservice.entities.Image;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ImageServiceInterface {
    String convertMultiPartToFile(MultipartFile file, ImageType imageType) throws IOException;

    File getImageByPath(String canonicalImagePath, ImageType cd) throws ImageException, URISyntaxException;

    void deleteImage(String canonicalImagePath, ImageType imageType) throws IOException, URISyntaxException;

    void deleteImage(String path) throws IOException;

    List<Image> getAll();
}
