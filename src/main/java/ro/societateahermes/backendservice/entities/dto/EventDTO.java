package ro.societateahermes.backendservice.entities.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String eventName;
    private String eventDescription;
    private LocalDateTime eventStartDate;
    private String eventEstimatedTime;
    private LocalDateTime eventEndDate;
    private String eventLink;
    private String eventLocation;


}


