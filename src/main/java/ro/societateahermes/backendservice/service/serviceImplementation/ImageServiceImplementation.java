package ro.societateahermes.backendservice.service.serviceImplementation;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.societateahermes.backendservice.entities.Image;
import ro.societateahermes.backendservice.entities.ImageType;
import ro.societateahermes.backendservice.repository.ImageRepositoryInterface;
import ro.societateahermes.backendservice.service.ImageServiceInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImplementation implements ImageServiceInterface {

    private final ImageRepositoryInterface imageRepository;

    public ImageServiceImplementation(ImageRepositoryInterface imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public String convertMultiPartToFile(MultipartFile file, ImageType imageType) throws IOException {
        UUID name = UUID.randomUUID();
        String imageName = name.toString();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        File convFile = new File("src/main/resources/images/" + imageType.getFolderName() + "/" + imageName
                + "." + extension);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile.getPath();
    }

    @Override
    public File getImageByPath(String imageName, ImageType imageType) throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource("images/" + imageType.getFolderName() + "/" + imageName);
        File file = Paths.get(res.toURI()).toFile();
        return file;
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public void deleteImage(String imageName, ImageType imageType) throws IOException, URISyntaxException {
        URL res = getClass().getClassLoader().getResource("images/" + imageType.getFolderName() + "/" + imageName);
        Files.delete(Paths.get(res.toURI()));
    }

    public void deleteImage(String imagePath) throws IOException {
        Files.delete(Path.of(imagePath));
    }
}
