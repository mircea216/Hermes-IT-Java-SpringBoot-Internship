package ro.societateahermes.backendservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.societateahermes.backendservice.entities.form.Form;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String eventDescription;
    private LocalDateTime eventStartDate;
    private String eventEstimatedTime;
    private LocalDateTime eventEndDate;
    private String eventLink;
    private String eventLocation;
    @OneToMany(mappedBy = "event")
    private List<Activity> listOfActivities = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<Participation> listOfParticipation = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "event")
    private List<Edition> editions = new ArrayList<>();
    @OneToMany(mappedBy = "event")
    private List<Sponsor> sponsorList;

    @JsonManagedReference
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Form form;
}
