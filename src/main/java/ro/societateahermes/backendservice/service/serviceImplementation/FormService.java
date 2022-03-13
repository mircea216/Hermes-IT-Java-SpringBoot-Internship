package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.dto.FormDto;
import ro.societateahermes.backendservice.entities.form.Form;
import ro.societateahermes.backendservice.entities.form.Option;
import ro.societateahermes.backendservice.entities.form.Question;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;
import ro.societateahermes.backendservice.repository.FormRepository;
import ro.societateahermes.backendservice.service.FormServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.FormMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService implements FormServiceInterface {
    private final FormRepository formRepository;
    private final EventRepositoryInterface eventRepository;

    public FormService(FormRepository formRepository, EventRepositoryInterface eventRepository) {
        this.formRepository = formRepository;
        this.eventRepository = eventRepository;
    }

    public List<FormDto> getAll() {
        List<Form> forms = formRepository.findAll();
        return forms.stream()
                .map(FormMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public FormDto getForm(Long eventId) {
        Form form = eventRepository.findById(eventId).orElseThrow().getForm();
        return FormMapper.convertToDtoWithQuestions(form);
    }

    public void save(Long eventId, FormDto formDto) {
        Form form = FormMapper.convertToEntity(formDto);
        for (Question question : form.getQuestions()) {
            question.setForm(form);

            for (Option option : question.getOptions()) {
                option.setQuestion(question);
            }
        }

        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setForm(form);
        form.setEvent(event);
        eventRepository.save(event);
    }

    public void delete(Long formId) {
        Event event = formRepository.findById(formId).orElseThrow().getEvent();
        event.setForm(null);
        eventRepository.save(event);
    }
}
