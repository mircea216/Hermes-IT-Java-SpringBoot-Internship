package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.societateahermes.backendservice.entities.Event;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullActivityDTO {
    private Long IdActivity;
    private Long IdEvent;
    private String activityName;
    private String activityDescription;
    private LocalDateTime activityStartDate;
    private String activityEstimatedTime;
    private LocalDateTime activityEndDate;
    private int maximumNumberOfParticipants;
    private String activityLink;

}
