package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.societateahermes.backendservice.entities.Event;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimelineDTO {
    @Id
    @GeneratedValue
    private Long IdTimeline;
    private Long IdEvent;
    private List<ActivityDTO> listOfActivities;
}
