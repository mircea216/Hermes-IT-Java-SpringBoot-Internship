package ro.societateahermes.backendservice.utils.mapper;

import org.springframework.stereotype.Component;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.dto.EventDTO;


@Component
public class EventMapper {

    public EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setEventName(event.getEventName());
        eventDTO.setEventDescription(event.getEventDescription());
        eventDTO.setEventLink(event.getEventLink());
        eventDTO.setEventStartDate(event.getEventStartDate());
        eventDTO.setEventEndDate(event.getEventEndDate());
        eventDTO.setEventEstimatedTime(event.getEventEstimatedTime());
        eventDTO.setEventLocation(event.getEventLocation());

        return eventDTO;

    }

    public Event convertToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setEventDescription(eventDTO.getEventDescription());
        event.setEventName(eventDTO.getEventName());
        event.setEventLink(eventDTO.getEventLink());
        event.setEventStartDate(eventDTO.getEventStartDate());
        event.setEventEndDate(eventDTO.getEventEndDate());
        event.setEventEstimatedTime(eventDTO.getEventEstimatedTime());
        event.setEventLocation(eventDTO.getEventLocation());

        return event;

    }
}
