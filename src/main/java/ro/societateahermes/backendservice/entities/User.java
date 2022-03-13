package ro.societateahermes.backendservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long ID;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String university;
    private String password;
    private String yearOfStudy;
    private String field;
    private String language;

    @OneToMany(mappedBy = "user")
    private List<Participation> listOfParticipation=new ArrayList<>();


}
