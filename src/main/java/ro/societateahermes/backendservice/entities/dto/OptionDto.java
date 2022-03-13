package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.societateahermes.backendservice.entities.form.Question;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDto {
    private Long id;

    private Question question;

    private String label;
}
