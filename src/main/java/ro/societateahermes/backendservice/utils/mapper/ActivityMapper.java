package ro.societateahermes.backendservice.utils.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.societateahermes.backendservice.entities.Activity;
import ro.societateahermes.backendservice.entities.dto.FullActivityDTO;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityMapper {
    @Autowired
    private EventRepositoryInterface eventRepository;

    public Activity activityDTOtoActivity(FullActivityDTO activity) {
        Activity activityFromDTO = new Activity();
        activityFromDTO.setIdActivity(activity.getIdActivity());
        activityFromDTO.setActivityLink(activity.getActivityLink());
        activityFromDTO.setActivityName(activity.getActivityName());
        activityFromDTO.setActivityDescription(activity.getActivityDescription());
        activityFromDTO.setActivityEndDate(activity.getActivityEndDate());
        activityFromDTO.setActivityStartDate(activity.getActivityStartDate());
        activityFromDTO.setActivityEstimatedTime(activity.getActivityEstimatedTime());
        activityFromDTO.setMaximumNumberOfParticipants(activity.getMaximumNumberOfParticipants());
        activityFromDTO.setEvent(eventRepository.findById(activity.getIdEvent()).orElseThrow());
        return activityFromDTO;
    }

    public List<FullActivityDTO> activitiesToActivitiesDTO(List<Activity> activities) {

        return activities.stream().map(activity -> activityToActivityDTO(activity)).collect(Collectors.toList());
    }

    public FullActivityDTO activityToActivityDTO(Activity activity) {
        FullActivityDTO fullActivityDTO = new FullActivityDTO();
        fullActivityDTO.setIdActivity(activity.getIdActivity());
        fullActivityDTO.setActivityLink(activity.getActivityLink());
        fullActivityDTO.setActivityName(activity.getActivityName());
        fullActivityDTO.setActivityDescription(activity.getActivityDescription());
        fullActivityDTO.setActivityEndDate(activity.getActivityEndDate());
        fullActivityDTO.setActivityStartDate(activity.getActivityStartDate());
        fullActivityDTO.setActivityEstimatedTime(activity.getActivityEstimatedTime());
        fullActivityDTO.setMaximumNumberOfParticipants(activity.getMaximumNumberOfParticipants());
        fullActivityDTO.setIdEvent(activity.getEvent().getId());
        return fullActivityDTO;
    }
}
