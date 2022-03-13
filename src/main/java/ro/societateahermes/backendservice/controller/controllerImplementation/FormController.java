package ro.societateahermes.backendservice.controller.controllerImplementation;

import org.springframework.web.bind.annotation.*;
import ro.societateahermes.backendservice.entities.dto.FormDto;
import ro.societateahermes.backendservice.exceptions.UnathorizeException;
import ro.societateahermes.backendservice.service.serviceImplementation.FormService;
import ro.societateahermes.backendservice.utils.PermissionChecker;
import ro.societateahermes.backendservice.utils.RolesActiveUser;

import java.util.List;

@RestController
@RequestMapping("/form")
public class FormController {
    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public List<FormDto> getAll() {
        return formService.getAll();
    }

    @GetMapping("/{eventId}")
    public FormDto getForm(@PathVariable Long eventId) {
        return formService.getForm(eventId);
    }

    @PostMapping("/{eventId}")
    public void save(@PathVariable Long eventId,
                     @RequestBody FormDto formDto) throws UnathorizeException {
        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(eventId, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        formService.save(eventId, formDto);
    }

    @DeleteMapping("/{eventId}/{formId}")
    public void delete(@PathVariable("eventId") long eventId, @PathVariable("formId") Long formId) throws UnathorizeException {

        List<String> roles = RolesActiveUser.getRoles();
        if (!PermissionChecker.check(eventId, roles)) {
            throw new UnathorizeException("User is not authorized");
        }
        formService.delete(formId);
    }
}
