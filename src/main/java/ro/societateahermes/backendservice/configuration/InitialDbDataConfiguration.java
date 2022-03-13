package ro.societateahermes.backendservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import ro.societateahermes.backendservice.entities.BoardRole;
import ro.societateahermes.backendservice.entities.Event;
import ro.societateahermes.backendservice.entities.Role;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;
import ro.societateahermes.backendservice.repository.RoleRepositoryInterface;

@Configuration
public class InitialDbDataConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(InitialDbDataConfiguration.class);

    public InitialDbDataConfiguration(EventRepositoryInterface eventRepository, RoleRepositoryInterface roleRepository) {
        insertEvents(eventRepository);
        insertRoles(roleRepository);
    }

    private void insertEvents(EventRepositoryInterface eventRepository) {
        Event cariere = new Event();
        cariere.setId(1L);
        cariere.setEventName("cariereinit");

        Event destresiune = new Event();
        destresiune.setId(2L);
        destresiune.setEventName("destresiune");

        Event hackathon = new Event();
        hackathon.setId(3L);
        hackathon.setEventName("hackathon");

        Event guideDays = new Event();
        guideDays.setId(4L);
        guideDays.setEventName("guidedays");

        eventRepository.save(cariere);
        eventRepository.save(destresiune);
        eventRepository.save(hackathon);
        eventRepository.save(guideDays);

        logger.info("Added " + eventRepository.findAll().size() + " custom events.");
    }

    private void insertRoles(RoleRepositoryInterface roleRepository) {
        roleRepository.save(new Role(1, BoardRole.USER));
        roleRepository.save(new Role(2, BoardRole.ADMIN));
        roleRepository.save(new Role(3, BoardRole.PROJECT_MANAGER_CARIEREIT));
        roleRepository.save(new Role(4, BoardRole.PROJECT_MANAGER_HACKATHON));
        roleRepository.save(new Role(5, BoardRole.PROJECT_MANAGER_DESTRESIUNE));
        roleRepository.save(new Role(6, BoardRole.PROJECT_MANAGER_GUIDEDAYS));

        logger.info("Added " + roleRepository.findAll().size() + " custom roles.");
    }
}
