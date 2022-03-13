package ro.societateahermes.backendservice.utils.mapper;

import ro.societateahermes.backendservice.entities.dto.FormDto;
import ro.societateahermes.backendservice.entities.dto.QuestionDto;
import ro.societateahermes.backendservice.entities.form.Form;
import ro.societateahermes.backendservice.entities.form.Question;

import java.util.List;
import java.util.stream.Collectors;

public class FormMapper {
    public static FormDto convertToDto(Form form) {
        List<QuestionDto> questionsDto = form.getQuestions().stream()
                .map(QuestionMapper::convertToDto)
                .collect(Collectors.toList());
        return new FormDto(form.getId(), form.getEvent(), questionsDto);
    }

    public static Form convertToEntity(FormDto formDto) {
        List<Question> questions = formDto.getQuestions().stream()
                .map(QuestionMapper::convertToEntity)
                .collect(Collectors.toList());
        return new Form(formDto.getId(), formDto.getEvent(), questions);
    }

    public static FormDto convertToDtoWithQuestions(Form form) {
        List<QuestionDto> questionsDto = form.getQuestions().stream()
                .map(QuestionMapper::convertToDto)
                .collect(Collectors.toList());
        return new FormDto(form.getId(), questionsDto);
    }
}
