package ro.societateahermes.backendservice.utils.mapper;

import org.mapstruct.Mapper;
import ro.societateahermes.backendservice.entities.Activity;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.dto.ActivityDTO;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ActivityMapperInterface {

    ActivityDTO convertToActivityDTO(Activity activity);

    Activity convertToActivity(ActivityDTO activityDTO);

    List<ActivityDTO> convertToActivityDTOS(List<Activity> activities);

    List<Activity> convertToActivities(List<ActivityDTO> activityDTOS);
}
