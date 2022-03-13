package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.EmailTemplates;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.Participation;

import java.io.IOException;
import java.util.Map;

public interface EmailServiceInterface {
    void sendAppropriateEmails();

    void configureAndSendReminderMessageDuringEvent(Event event);

    void configureAndSendConfirmationEmail(Participation participation) throws IOException;

    void configureAndSendReminderMessageBeforeEvent(Event event, Integer daysBefore);

    String getReplacedThymeleafTemplate(Map<String, Object> templateModel, EmailTemplates emailTemplate);
}
