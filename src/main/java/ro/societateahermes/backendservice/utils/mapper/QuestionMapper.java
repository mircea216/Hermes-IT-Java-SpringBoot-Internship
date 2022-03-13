package ro.societateahermes.backendservice.utils.mapper;

import ro.societateahermes.backendservice.entities.dto.OptionDto;
import ro.societateahermes.backendservice.entities.dto.QuestionDto;
import ro.societateahermes.backendservice.entities.form.Option;
import ro.societateahermes.backendservice.entities.form.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {
    public static QuestionDto convertToDto(Question question) {
        List<OptionDto> optionsDto = question.getOptions().stream()
                .map(OptionMapper::convertToDto)
                .collect(Collectors.toList());
        return new QuestionDto(question.getId(), question.getForm(), question.getLabel(),
                question.getQuestionTypeId(), optionsDto);
    }

    public static Question convertToEntity(QuestionDto questionDto) {
        List<Option> options = questionDto.getOptions().stream()
                .map(OptionMapper::convertToEntity)
                .collect(Collectors.toList());
        return new Question(questionDto.getId(), questionDto.getForm(), questionDto.getLabel(),
                questionDto.getQuestionTypeId(), options);
    }
}
