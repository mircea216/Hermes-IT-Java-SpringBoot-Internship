package ro.societateahermes.backendservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Timeline {
    @Id
    @GeneratedValue
    private Long IdTimeline;
    private Long IdEvent;
    @OneToMany(mappedBy = "timeline")
    private List<Activity> listOfActivities=new ArrayList<>();
}