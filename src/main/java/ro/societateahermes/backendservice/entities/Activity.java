package ro.societateahermes.backendservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Activity {
    @Id
    @GeneratedValue
    private Long IdActivity;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    private String activityName;
    private String activityDescription;
    private LocalDateTime activityStartDate;
    private String activityEstimatedTime;
    private LocalDateTime activityEndDate;
    private int maximumNumberOfParticipants;
    private String activityLink;
    private String activityLocation;
    @ManyToOne
    @JoinColumn(name = "timeline_id")
    private Timeline timeline;
}
