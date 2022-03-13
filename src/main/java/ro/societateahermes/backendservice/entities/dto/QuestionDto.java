package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.societateahermes.backendservice.entities.form.Form;
import ro.societateahermes.backendservice.entities.form.QuestionType;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionDto {
    private Long id;

    private Form form;

    private String label;

    private Integer questionTypeId;

    private List<OptionDto> options;

    public QuestionType getQuestionType() {
        return QuestionType.getType(questionTypeId);
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionTypeId = questionType == null ? null : questionType.getId();
    }
}
