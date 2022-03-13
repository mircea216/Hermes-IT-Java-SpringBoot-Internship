package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.Participation;
import ro.societateahermes.backendservice.entities.dto.EventDTO;
import ro.societateahermes.backendservice.entities.dto.NotificationSwitchDTO;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;
import ro.societateahermes.backendservice.repository.ParticipationRepositoryInterface;
import ro.societateahermes.backendservice.service.EventServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.EventMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventServiceImplementation implements EventServiceInterface {

    private final EventRepositoryInterface eventRepository;

    @Autowired
    public EventServiceImplementation(EventRepositoryInterface eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    private ParticipationRepositoryInterface participationRepository;

    @Autowired
    private EventMapper eventMapper;

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void addParticipation(long eventID, Participation participation) {
        Event event = eventRepository.getOne(eventID);
        List<Participation> participationList = event.getListOfParticipation();
        participationList.add(participation);
        event.setListOfParticipation(participationList);
    }

    public List<Participation> getParticipationsOfEvent(Event event) {
        return participationRepository.findParticipationByEvent(event);
    }

    public NotificationSwitchDTO getEventStatusByEventName(String eventName) {
        Optional<Event> eventOptional = eventRepository.findByEventName(eventName);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (event.getEventStartDate().isAfter(LocalDateTime.now())) {
                return new NotificationSwitchDTO("Event has not started yet", false);
            }
            if (event.getEventEndDate().isAfter(LocalDateTime.now())) {
                return new NotificationSwitchDTO("Event has started", true);
            }
            return new NotificationSwitchDTO("Event has finished", false);

        }
        return new NotificationSwitchDTO("Event not found", false);
    }

    public boolean isDaysBeforeEvent(Event event, Integer daysBefore) {
        LocalDateTime eventStartDate = event.getEventStartDate();
        Period period = Period.between(LocalDate.now(), eventStartDate.toLocalDate());
        return period.getDays() == daysBefore;
    }

    public boolean isDuringEvent(Event event) {
        LocalDateTime now = LocalDateTime.now();
        return event.getEventStartDate().isBefore(now) && event.getEventEndDate().isAfter(now);
    }

    @Override
    public EventDTO getOne(String eventName) {
        return eventMapper.convertToDTO(eventRepository.findByEventName(eventName).orElseThrow());
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(event -> eventMapper.convertToDTO(event)).collect(Collectors.toList());
    }

    @Override
    public void update(EventDTO eventDTO) {
        eventRepository.save(eventMapper.convertToEvent(eventDTO));
    }
}
