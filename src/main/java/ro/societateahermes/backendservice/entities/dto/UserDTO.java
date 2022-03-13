package ro.societateahermes.backendservice.entities.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long ID;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String university;
    private String yearOfStudy;
    private String field;
    private String language;
}
