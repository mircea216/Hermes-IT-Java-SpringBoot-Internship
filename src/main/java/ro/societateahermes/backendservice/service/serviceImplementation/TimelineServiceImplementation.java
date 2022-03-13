package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.dto.ActivityDTO;
import ro.societateahermes.backendservice.entities.dto.TimelineDTO;
import ro.societateahermes.backendservice.utils.mapper.TimelineMapperInterface;
import ro.societateahermes.backendservice.repository.TimelineRepositoryInterface;
import ro.societateahermes.backendservice.service.TimelineServiceInterface;

import java.util.Comparator;
import java.util.List;

@Service
public class TimelineServiceImplementation implements TimelineServiceInterface {

    private final TimelineRepositoryInterface timelineRepository;
    private final TimelineMapperInterface timelineMapper;

    @Autowired
    public TimelineServiceImplementation(TimelineRepositoryInterface timelineRepository, TimelineMapperInterface timelineMapper) {
        this.timelineRepository = timelineRepository;
        this.timelineMapper = timelineMapper;
    }

    @Override
    public void save(TimelineDTO timelineDTO) {
        timelineRepository.save(timelineMapper.convertToTimeline(timelineDTO));
    }

    @Override
    public void delete(TimelineDTO timelineDTO) {
        timelineRepository.delete(timelineMapper.convertToTimeline(timelineDTO));
    }

    @Override
    public TimelineDTO getTimelineOfEvent(Long IdEvent) {
        TimelineDTO timelineDTO = new TimelineDTO();

        for (TimelineDTO timelineSelect : getAllActivityFromTimeline()) {
            if(timelineSelect.getIdEvent().equals(IdEvent))
                timelineDTO = timelineSelect;
        }
        return timelineDTO;
    }

    @Override
    public List<TimelineDTO> getAllActivityFromTimeline() {
        return timelineMapper.convertToTimelineDTOS(timelineRepository.findAll());
    }

    @Override
    public TimelineDTO createActivityFromTimeline(Long IdEvent, ActivityDTO activityDTO) {
        TimelineDTO timelineDTO = getTimelineOfEvent(IdEvent);
        timelineDTO.getListOfActivities().add(activityDTO);
        save(timelineDTO);

        return timelineDTO;
    }

    @Override
    public TimelineDTO updateActivityFromTimeline(Long IdEvent, ActivityDTO activityDTO) {
        TimelineDTO timelineDTO = getTimelineOfEvent(IdEvent);
        ActivityDTO activity = new ActivityDTO();

        for(ActivityDTO activitySelect : timelineDTO.getListOfActivities())
            if(activitySelect.getIdActivity().equals(activityDTO.getIdActivity())){
                activity.setActivityName(activityDTO.getActivityName());
                activity.setActivityStartDate(activityDTO.getActivityStartDate());
                activity.setActivityLocation(activityDTO.getActivityLocation());
            }

        timelineDTO.getListOfActivities().add(activity);
        save(timelineDTO);

        return timelineDTO;
    }

    @Override
    public void deleteActivityFromTimeline(Long IdEvent, ActivityDTO activityDTO) {
        TimelineDTO timelineDTO = getTimelineOfEvent(IdEvent);
        timelineDTO.getListOfActivities().remove(activityDTO);
        save(timelineDTO);
    }

    @Override
    public ActivityDTO getOneActivityFromTimeline(Long IdEvent, Long IdActivity) {
        TimelineDTO timelineDTO = getTimelineOfEvent(IdEvent);

        ActivityDTO activityDTO = new ActivityDTO();
        for(ActivityDTO activitySelect : timelineDTO.getListOfActivities()) {
            if (activitySelect.getIdActivity().equals(IdActivity))
                activityDTO = activitySelect;
        }

        return activityDTO;
    }

    @Override
    public List<ActivityDTO> orderActivityByDateAndTimeFromTimeline(Long IdEvent){
        TimelineDTO timelineDTO = getTimelineOfEvent(IdEvent);
        timelineDTO.getListOfActivities().sort(Comparator.comparing(ActivityDTO::getActivityStartDate));

        return timelineDTO.getListOfActivities();
    }
}

