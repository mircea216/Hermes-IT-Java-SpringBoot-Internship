package ro.societateahermes.backendservice.controller.controllerImplementation;

import org.springframework.web.bind.annotation.*;
import ro.societateahermes.backendservice.controller.ActivityControllerInterface;
import ro.societateahermes.backendservice.entities.dto.FullActivityDTO;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;
import ro.societateahermes.backendservice.service.ActivityServiceInterface;
import ro.societateahermes.backendservice.service.serviceImplementation.ActivityServiceImplementation;
import ro.societateahermes.backendservice.utils.PermissionChecker;
import ro.societateahermes.backendservice.utils.RolesActiveUser;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController implements ActivityControllerInterface {
    private final ActivityServiceInterface activityService;

    public ActivityController(ActivityServiceImplementation activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/{eventId}")
    public void save(@PathVariable Long eventId, @RequestBody FullActivityDTO fullActivityDTO) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(eventId, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        activityService.save(fullActivityDTO);
    }

    @GetMapping
    public List<FullActivityDTO> getAll() {
        return activityService.getAllActivities();
    }

    @DeleteMapping("/{eventId}/{activityId}")
    public void delete(@PathVariable Long eventId, @PathVariable("activityId") Long activityId) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(eventId, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        activityService.delete(activityId);
    }

    @PutMapping("/{eventId}")
    public void put(@PathVariable Long eventId, @RequestBody FullActivityDTO fullActivityDTO) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(eventId, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        activityService.update(fullActivityDTO);
    }
}
