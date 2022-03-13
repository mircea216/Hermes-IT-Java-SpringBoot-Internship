package ro.societateahermes.backendservice.controller.controllerImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.societateahermes.backendservice.controller.TimelineControllerInterface;
import ro.societateahermes.backendservice.entities.dto.ActivityDTO;
import ro.societateahermes.backendservice.entities.dto.TimelineDTO;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;
import ro.societateahermes.backendservice.service.ActivityServiceInterface;
import ro.societateahermes.backendservice.service.TimelineServiceInterface;
import ro.societateahermes.backendservice.utils.PermissionChecker;
import ro.societateahermes.backendservice.utils.RolesActiveUser;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/timeline")
public class TimelineControllerImplementation implements TimelineControllerInterface {

    private final TimelineServiceInterface timeLineService;

    private final ActivityServiceInterface activityService;

    @Autowired
    public TimelineControllerImplementation(TimelineServiceInterface timeLineService, ActivityServiceInterface activityService) {
        this.timeLineService = timeLineService;
        this.activityService = activityService;
    }


    @PostMapping("/{IdEvent}")
    @Override
    public void createActivityFromTimeline(@Valid @PathVariable("IdEvent") Long IdEvent, @Valid @RequestBody ActivityDTO activityDTO) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(IdEvent, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        activityService.save(activityDTO);
        TimelineDTO timelineDTO = timeLineService.createActivityFromTimeline(IdEvent, activityDTO);
        activityService.update(activityDTO, timelineDTO);
    }

    @PutMapping("/{IdEvent}")
    @Override
    public void updateActivityFromTimeline(@Valid @PathVariable("IdEvent") Long IdEvent, @Valid @RequestBody ActivityDTO activityDTO) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(IdEvent, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        activityService.save(activityDTO);
        TimelineDTO timelineDTO = timeLineService.updateActivityFromTimeline(IdEvent, activityDTO);
        activityService.update(activityDTO, timelineDTO);
    }

    @DeleteMapping("/{IdEvent}")
    @Override
    public void deleteActivityFromTimeline(@Valid @PathVariable("IdEvent") Long IdEvent, @Valid @RequestBody ActivityDTO activityDTO) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(IdEvent, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        activityService.delete(activityDTO);
        timeLineService.deleteActivityFromTimeline(IdEvent, activityDTO);
    }

    @GetMapping("/{IdEvent}/{IdActivity}")
    @Override
    public ActivityDTO getOneActivityFromTimeline(@Valid @PathVariable("IdEvent") Long IdEvent, @Valid @PathVariable("IdActivity") Long IdActivity) {
        return timeLineService.getOneActivityFromTimeline(IdEvent, IdActivity);
    }

    @GetMapping
    @Override
    public List<TimelineDTO> getAllActivityFromTimeline() {
        return timeLineService.getAllActivityFromTimeline();
    }

    @GetMapping("/{IdEvent}")
    @Override
    public List<ActivityDTO> getAllActivityFromTimelineOrderByDateAndTime(@Valid @PathVariable("IdEvent") Long IdEvent) {
        return timeLineService.orderActivityByDateAndTimeFromTimeline(IdEvent);
    }
}


