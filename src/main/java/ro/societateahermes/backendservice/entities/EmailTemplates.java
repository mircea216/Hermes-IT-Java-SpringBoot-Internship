package ro.societateahermes.backendservice.entities;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public enum EmailTemplates {
    CONFIRMATION("Confirmation email", "confirmation-email.html"),
    BEFORE_EVENT_REMINDER("Before event reminder email", "before-event-reminder-email.html"),
    DURING_EVENT_REMINDER("During event reminder email", "during-event-reminder-email.html");

    private static final String EMAIL_TEMPLATES_FOLDER = "email-templates/";
    private final String name;
    private final String templateName;

    EmailTemplates(String name, String templateName) {
        this.name = name;
        this.templateName = templateName;
    }

    public String getTemplate() throws IOException {
        return new String(new ClassPathResource(EMAIL_TEMPLATES_FOLDER + templateName)
                .getInputStream().readAllBytes(),
                StandardCharsets.UTF_8);
    }

    public String getName() {
        return name;
    }

    public String getTemplateName() {
        return templateName;
    }
}
