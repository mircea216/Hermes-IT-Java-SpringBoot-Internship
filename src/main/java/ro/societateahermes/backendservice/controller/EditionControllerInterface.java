package ro.societateahermes.backendservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ro.societateahermes.backendservice.entities.EditionMediaDeletion;
import ro.societateahermes.backendservice.entities.EditionMediaUpload;
import ro.societateahermes.backendservice.entities.dto.EditionDto;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;

import java.io.IOException;
import java.util.List;

public interface EditionControllerInterface {
    List<EditionDto> getAll();

    void saveNewEditionWithMedia(@PathVariable Long eventId,
                                 @ModelAttribute EditionMediaUpload editionMediaUpload) throws UnathorizeException;

    void deleteEdition(@PathVariable("editionId") Long editionId) throws IOException, UnathorizeException;

    ResponseEntity<?> getImageById(@PathVariable("imageId") Long imageId) throws IOException;

    ResponseEntity<Object> addMediaToEdition(@PathVariable("editionId") Long editionId,
                                             @ModelAttribute EditionMediaUpload editionMediaUpload) throws UnathorizeException;

    ResponseEntity<Object> deleteMediaFromEdition(@PathVariable("editionId") Long editionId,
                                                  @RequestBody EditionMediaDeletion editionMediaDeletion) throws IOException, UnathorizeException;
}
