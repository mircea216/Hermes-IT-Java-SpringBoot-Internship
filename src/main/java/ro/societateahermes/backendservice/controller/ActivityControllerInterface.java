package ro.societateahermes.backendservice.controller;

import ro.societateahermes.backendservice.entities.dto.FullActivityDTO;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;

import java.util.List;

public interface ActivityControllerInterface {
    void save(Long eventId, FullActivityDTO activity) throws UnathorizeException;

    List<FullActivityDTO> getAll();

    void delete(Long eventId, Long activityId) throws UnathorizeException;

    void put(Long eventId, FullActivityDTO fullActivityDTO) throws UnathorizeException;
}
