package ro.societateahermes.backendservice.utils.mapper.mapperImplementation;

import org.springframework.stereotype.Component;
import ro.societateahermes.backendservice.entities.Activity;
import ro.societateahermes.backendservice.entities.dto.ActivityDTO;
import ro.societateahermes.backendservice.utils.mapper.ActivityMapperInterface;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-07-20T13:12:44+0100",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11"
)

@Component
public class ActivityMapperImplementation implements ActivityMapperInterface {
    
    @Override
    public ActivityDTO convertToActivityDTO(Activity activity) {

        if(activity == null)
            return null;

        ActivityDTO activityDTO = new ActivityDTO();

        activityDTO.setIdActivity(activity.getIdActivity());
        activityDTO.setActivityName(activity.getActivityName());
        activityDTO.setActivityStartDate(activity.getActivityStartDate());
        activityDTO.setActivityLocation(activity.getActivityLocation());

        return activityDTO;
    }

    @Override
    public Activity convertToActivity(ActivityDTO activityDTO) {
        if(activityDTO == null)
            return null;

        Activity activity = new Activity();

        activity.setIdActivity(activityDTO.getIdActivity());
        activity.setActivityName(activityDTO.getActivityName());
        activity.setActivityStartDate(activityDTO.getActivityStartDate());
        activity.setActivityLocation(activityDTO.getActivityLocation());

        return activity;
    }

    @Override
    public List<ActivityDTO> convertToActivityDTOS(List<Activity> activities) {

        if (activities == null )
            return null;

        List<ActivityDTO> activityDTOS = new ArrayList<ActivityDTO>(activities.size());
        for (Activity activity : activities ) {
            activityDTOS.add(convertToActivityDTO(activity));
        }

        return activityDTOS;
    }

    @Override
    public List<Activity> convertToActivities(List<ActivityDTO> activityDTOS) {
        if (activityDTOS == null )
            return null;

        List<Activity> activities = new ArrayList<Activity>(activityDTOS.size());
        for (ActivityDTO activityDTO : activityDTOS ) {
            activities.add(convertToActivity(activityDTO));
        }

        return activities;
    }
}
