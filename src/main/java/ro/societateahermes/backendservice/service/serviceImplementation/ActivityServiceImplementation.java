package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.Activity;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.Timeline;
import ro.societateahermes.backendservice.entities.dto.ActivityDTO;
import ro.societateahermes.backendservice.entities.dto.FullActivityDTO;
import ro.societateahermes.backendservice.entities.dto.TimelineDTO;
import ro.societateahermes.backendservice.repository.ActivityRepositoryInterface;
import ro.societateahermes.backendservice.service.ActivityServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.ActivityMapper;
import ro.societateahermes.backendservice.utils.mapper.ActivityMapperInterface;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImplementation implements ActivityServiceInterface {

    private final ActivityRepositoryInterface activityRepository;
    private final ActivityMapperInterface activityMapper;
    private final ActivityMapper fullActivityMapper;

    @Autowired
    public ActivityServiceImplementation(ActivityRepositoryInterface activityRepository, ActivityMapperInterface activityMapper, ActivityMapper fullActivityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
        this.fullActivityMapper = fullActivityMapper;
    }

    @Override
    public void save(ActivityDTO activityDTO) {
        activityRepository.save(activityMapper.convertToActivity(activityDTO));
    }

    public void save(FullActivityDTO activityDTO) {
        activityRepository.save(fullActivityMapper.activityDTOtoActivity(activityDTO));
    }

    @Override
    public void delete(ActivityDTO activityDTO) {
        activityRepository.delete(activityMapper.convertToActivity(activityDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        for (Activity activity : activityRepository.findAll()) {
            if (activity.getIdActivity() == id) {
                activityRepository.delete(activity);
                return;
            }
        }
    }

    @Override
    @Transactional
    public void update(FullActivityDTO activity) {
        if (activity != null) {
            Activity updatedActivity = fullActivityMapper.activityDTOtoActivity(activity);
            activityRepository.save(updatedActivity);
        }
    }

    @Override
    public List<ActivityDTO> eventIsOngoing(Activity activity) {
        List<ActivityDTO> activities = new ArrayList<>();

        LocalDate startDate = LocalDate.from(activity.getActivityStartDate());
        LocalTime startTime = LocalTime.from(activity.getActivityStartDate());

        LocalDate endDate = LocalDate.from(activity.getActivityEndDate());
        LocalTime endTime = LocalTime.from(activity.getActivityEndDate());

        if (startDate.equals(LocalDate.now()) || LocalDate.now().isBefore(endDate)) {
            if (startTime.equals(LocalTime.now()) || LocalTime.now().isBefore(endTime))
                activities = activityMapper.convertToActivityDTOS(activityRepository.findAll());
        }

        return activities;
    }

    @Override
    public void update(ActivityDTO activityDTO, TimelineDTO timelineDTO) {
        Activity activity = activityMapper.convertToActivity(activityDTO);

        Event event = new Event();
        event.setId(timelineDTO.getIdEvent());

        Timeline timeline = new Timeline();
        timeline.setIdTimeline(timelineDTO.getIdTimeline());

        activity.setEvent(event);
        activity.setTimeline(timeline);

        activityRepository.save(activity);
    }

    @Override
    public List<FullActivityDTO> getAllActivities() {
        return fullActivityMapper.activitiesToActivitiesDTO(activityRepository.findAll());
    }

}
