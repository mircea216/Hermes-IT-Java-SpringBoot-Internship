package ro.societateahermes.backendservice.utils.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ro.societateahermes.backendservice.entities.User;
import ro.societateahermes.backendservice.entities.dto.AnswerDTO;
import ro.societateahermes.backendservice.entities.dto.MySubmissionDTO;
import ro.societateahermes.backendservice.entities.form.Answer;


@NoArgsConstructor
public class SubmissionMapper {


    public User convertToUser(MySubmissionDTO submissionDTO) {

        User user = new User();
        user.setFirstName(submissionDTO.getFirstName());
        user.setLastName(submissionDTO.getLastName());
        user.setEmail(submissionDTO.getEmail());
        user.setUsername(submissionDTO.getUsername());
        user.setPassword(submissionDTO.getPassword());
        user.setUniversity(submissionDTO.getUniversity());

        return user;
    }

    public Answer converToAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        return answer;
    }


}
