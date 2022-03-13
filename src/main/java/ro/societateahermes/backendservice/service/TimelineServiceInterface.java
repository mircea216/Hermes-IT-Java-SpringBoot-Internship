package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.dto.ActivityDTO;
import ro.societateahermes.backendservice.entities.dto.TimelineDTO;

import java.util.List;

public interface TimelineServiceInterface {

    void save(TimelineDTO timelineDTO);

    void delete(TimelineDTO timelineDTO);

    List<TimelineDTO> getAllActivityFromTimeline();

    TimelineDTO getTimelineOfEvent(Long IdEvent);

    TimelineDTO createActivityFromTimeline(Long IdEvent, ActivityDTO eventDTO);

    TimelineDTO updateActivityFromTimeline(Long IdEvent, ActivityDTO activityDTO);

    void deleteActivityFromTimeline(Long IdEvent, ActivityDTO eventDTO);

    ActivityDTO getOneActivityFromTimeline(Long IdEvent, Long IdActivity);

    List<ActivityDTO> orderActivityByDateAndTimeFromTimeline(Long IdEvent);
}
