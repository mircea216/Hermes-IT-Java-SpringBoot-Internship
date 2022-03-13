package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.dto.MySubmissionDTO;
import ro.societateahermes.backendservice.entities.dto.UserDTO;
import ro.societateahermes.backendservice.entities.Participation;
import ro.societateahermes.backendservice.entities.User;

import java.util.List;

public interface UserServiceInterface {
    void save(UserDTO user);
    User saveUserFromDTO(MySubmissionDTO user);

    void addParticipation(User user, Participation participation);

    List<UserDTO> getAllUsers();
    void delete(Long id);

    void update(UserDTO user);
}
