package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    @Id
    @GeneratedValue
    private Long IdActivity;
    private String activityName;
    private LocalDateTime activityStartDate;
    private String activityLocation;
}
