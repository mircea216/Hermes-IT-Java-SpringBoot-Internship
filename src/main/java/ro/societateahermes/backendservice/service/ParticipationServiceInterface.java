package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.dto.MySubmissionDTO;
import ro.societateahermes.backendservice.entities.dto.UserDTO;
import ro.societateahermes.backendservice.entities.Participation;
import ro.societateahermes.backendservice.entities.User;

import java.util.List;

public interface ParticipationServiceInterface {

    Participation savefromDTO(User user, MySubmissionDTO submissionDTO);

    List<UserDTO> getAllUsersFromEvent(Long eventId);

    void deleteByUserId(Long userId);
}
