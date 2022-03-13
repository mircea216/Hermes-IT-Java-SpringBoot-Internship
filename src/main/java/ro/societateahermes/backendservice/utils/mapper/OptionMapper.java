package ro.societateahermes.backendservice.utils.mapper;

import ro.societateahermes.backendservice.entities.dto.OptionDto;
import ro.societateahermes.backendservice.entities.form.Option;

public class OptionMapper {
    public static OptionDto convertToDto(Option option) {
        return new OptionDto(option.getId(), option.getQuestion(), option.getLabel());
    }

    public static Option convertToEntity(OptionDto optionDto) {
        return new Option(optionDto.getId(), optionDto.getQuestion(), optionDto.getLabel());
    }
}
