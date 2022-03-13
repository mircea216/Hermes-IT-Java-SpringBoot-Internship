package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ro.societateahermes.backendservice.entities.*;
import ro.societateahermes.backendservice.service.EmailServiceInterface;
import ro.societateahermes.backendservice.service.EventServiceInterface;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImplementation implements EmailServiceInterface {
    @Value("${spring.mail.username}")
    private String FROM;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Autowired
    private EventServiceInterface eventService;

    @Scheduled(cron = "0 0 7 * * *")
    public void sendAppropriateEmails() {
        if (FROM.equals("")) {
            return;
        }

        for (Event event : eventService.getAll()) {
            if (eventService.isDaysBeforeEvent(event, 3)) {
                this.configureAndSendReminderMessageBeforeEvent(event, 3);
            } else if (eventService.isDaysBeforeEvent(event, 1)) {
                this.configureAndSendReminderMessageBeforeEvent(event, 1);
            } else if (eventService.isDuringEvent(event)) {
                this.configureAndSendReminderMessageDuringEvent(event);
            }
        }
    }

    public void configureAndSendReminderMessageDuringEvent(Event event) {
        for (Participation participation : eventService.getParticipationsOfEvent(event)) {
            User user = participation.getUser();
            String recipientName = user.getFirstName() + " " + user.getLastName();
            String to = user.getEmail();

            Email email = new Email(to, recipientName,
                    "Reminder pe parcursul evenimentului " + event.getEventName());

            Map<String, Object> optionalVariables = new HashMap<>();
            optionalVariables.put("recipientName", email.getRecipientName());
            optionalVariables.put("eventName", event.getEventName());
            optionalVariables.put("eventLocation", event.getEventLocation());

            String replacedThymeleafTemplate = this.getReplacedThymeleafTemplate(optionalVariables, EmailTemplates.DURING_EVENT_REMINDER);
            this.sendHtmlMessage(email, replacedThymeleafTemplate);
        }
    }

    public void configureAndSendReminderMessageBeforeEvent(Event event, Integer daysBefore) {
        for (Participation participation : eventService.getParticipationsOfEvent(event)) {
            User user = participation.getUser();
            String recipientName = user.getFirstName() + " " + user.getLastName();
            String to = user.getEmail();

            Email email = new Email(to, recipientName, "Reminder pentru evenimentul " + event.getEventName());

            Map<String, Object> optionalVariables = new HashMap<>();
            optionalVariables.put("recipientName", email.getRecipientName());
            optionalVariables.put("daysBefore", daysBefore);
            optionalVariables.put("eventName", event.getEventName());
            optionalVariables.put("eventStartDate", event.getEventStartDate());
            optionalVariables.put("eventEndDate", event.getEventEndDate());
            optionalVariables.put("eventLocation", event.getEventLocation());

            String replacedThymeleafTemplate = this.getReplacedThymeleafTemplate(optionalVariables, EmailTemplates.BEFORE_EVENT_REMINDER);
            this.sendHtmlMessage(email, replacedThymeleafTemplate);
        }
    }

    public void configureAndSendConfirmationEmail(Participation participation) {
        if (FROM.equals("")) {
            return;
        }

        User user = participation.getUser();
        Event event = participation.getEvent();

        String recipientName = user.getFirstName() + " " + user.getLastName();
        Email email = new Email(user.getEmail(),
                recipientName,
                "Confirmare de inscriere la evenimentul " + event.getEventName());

        Map<String, Object> optionalVariables = new HashMap<>();
        optionalVariables.put("recipientName", recipientName);
        optionalVariables.put("eventName", event.getEventName());
        optionalVariables.put("eventStartDate", event.getEventStartDate());
        optionalVariables.put("eventEndDate", event.getEventEndDate());
        optionalVariables.put("eventLocation", event.getEventLocation());

        String replacedThymeleafTemplate = this.getReplacedThymeleafTemplate(optionalVariables, EmailTemplates.CONFIRMATION);
        this.sendHtmlMessage(email, replacedThymeleafTemplate);
    }

    public String getReplacedThymeleafTemplate(Map<String, Object> templateModel, EmailTemplates emailTemplate) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);

        return thymeleafTemplateEngine.process(emailTemplate.getTemplateName(), thymeleafContext);
    }

    private void sendHtmlMessage(Email email, String templateContent) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(FROM);
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(templateContent, true);
//          helper.addInline("attachment.png", resourceFile);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
