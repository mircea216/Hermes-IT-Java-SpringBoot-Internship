package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySubmissionDTO {

    //fields related to the user
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "Email invalid!")
    private String email;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Firstname is mandatory")
    @Pattern(regexp = "[a-zA-Z]+", message = "Firstname cannot contain numbers")
    private String firstName;
    @NotBlank(message = "Lastname is mandatory")
    @Pattern(regexp = "[a-zA-Z]+", message = "Lastname cannot contain numbers")
    private String lastName;
    @NotBlank(message = "University is mandatory")
    private String university;
    @NotBlank(message = "Password is mandatory")
    private String password;

    //fields related to the submission of the form
    private long formId;

    private List<AnswerDTO> answers;

    //fields related to the event
    private long eventId;


}
