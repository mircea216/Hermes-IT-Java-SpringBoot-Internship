package ro.societateahermes.backendservice.service.serviceImplementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.Participation;
import ro.societateahermes.backendservice.entities.User;
import ro.societateahermes.backendservice.entities.dto.MySubmissionDTO;
import ro.societateahermes.backendservice.entities.dto.UserDTO;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;
import ro.societateahermes.backendservice.repository.ParticipationRepositoryInterface;
import ro.societateahermes.backendservice.repository.UserRepositoryInterface;
import ro.societateahermes.backendservice.service.ParticipationServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipationServiceImplementation implements ParticipationServiceInterface {

    @Autowired
    private UserRepositoryInterface userRepository;

    @Autowired
    private EventRepositoryInterface eventRepository;

    @Autowired
    private ParticipationRepositoryInterface participationRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Participation savefromDTO(User user, MySubmissionDTO submissionDTO) {

        Event event = eventRepository.getOne(submissionDTO.getEventId());
        Participation participation = new Participation();
        participation.setEvent(event);
        participation.setUser(user);

        return participationRepository.save(participation);


    }

    @Override
    public List<UserDTO> getAllUsersFromEvent(Long eventId) {
        List<UserDTO> users = new ArrayList<>();

        for (Participation participation : participationRepository.findAll()) {
            if (participation.getEvent().getId() == eventId)
                users.add(modelMapper.map(participation.getUser(), UserDTO.class));

        }
        return users;

    }

    @Override
    public void deleteByUserId(Long userId) {
        Participation participation = participationRepository.findByUserID(userId);
        participationRepository.delete(participation);
    }
}
