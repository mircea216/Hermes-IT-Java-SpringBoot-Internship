package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.societateahermes.backendservice.entities.Event;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDto {
    private Long id;

    private Event event;

    public FormDto(Long id, List<QuestionDto> questionsDto) {
        this.id = id;
        this.questions = questionsDto;
    }

    private List<QuestionDto> questions;
}
