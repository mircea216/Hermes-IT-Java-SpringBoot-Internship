package ro.societateahermes.backendservice.entities;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class EditionMediaUpload {
    private List<MultipartFile> images;

    private List<MultipartFile> videos;
}
