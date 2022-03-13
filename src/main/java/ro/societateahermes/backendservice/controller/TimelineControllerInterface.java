package ro.societateahermes.backendservice.controller;

import ro.societateahermes.backendservice.entities.dto.ActivityDTO;
import ro.societateahermes.backendservice.entities.dto.TimelineDTO;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;

import java.util.List;

public interface TimelineControllerInterface {

    void createActivityFromTimeline(Long IdEvent, ActivityDTO activityDTO) throws UnathorizeException;

    void updateActivityFromTimeline(Long IdEvent, ActivityDTO activityDTO) throws UnathorizeException;

    void deleteActivityFromTimeline(Long IdEvent, ActivityDTO activityDTO) throws UnathorizeException;

    ActivityDTO getOneActivityFromTimeline(Long IdEvent, Long IdActivity);

    List<TimelineDTO> getAllActivityFromTimeline();
    List<ActivityDTO> getAllActivityFromTimelineOrderByDateAndTime(Long IdEvent);
}
