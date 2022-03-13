package ro.societateahermes.backendservice.controller.controllerImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.societateahermes.backendservice.entities.dto.EventDTO;
import ro.societateahermes.backendservice.entities.dto.NotificationSwitchDTO;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;
import ro.societateahermes.backendservice.service.EventServiceInterface;
import ro.societateahermes.backendservice.utils.PermissionChecker;
import ro.societateahermes.backendservice.utils.RolesActiveUser;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private final EventServiceInterface eventServiceInterface;

    public EventController(EventServiceInterface eventServiceInterface) {
        this.eventServiceInterface = eventServiceInterface;
    }

    @GetMapping("/status/{eventName}")
    public NotificationSwitchDTO getEventStatus(@PathVariable("eventName") String eventName) {
        return eventServiceInterface.getEventStatusByEventName(eventName);
    }

    @GetMapping("/{eventName}")
    public EventDTO getOne(@PathVariable("eventName") String eventName) {
        return eventServiceInterface.getOne(eventName);
    }

    @GetMapping()
    public List<EventDTO> getAll() {
        return eventServiceInterface.getAllEvents();
    }

    @PutMapping()
    public void update(@RequestBody EventDTO eventDTO) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.checkAdmin(roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        eventServiceInterface.update(eventDTO);
    }
}
