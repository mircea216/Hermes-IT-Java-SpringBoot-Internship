package ro.societateahermes.backendservice.utils.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.societateahermes.backendservice.entities.Sponsor;
import ro.societateahermes.backendservice.entities.dto.SponsorDTO;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;

@Component
public class SponsorMapper {

    @Autowired
    private EventRepositoryInterface eventRepository;

    public SponsorDTO convertToDTO(Sponsor sponsor) {
        SponsorDTO sponsorDTO = new SponsorDTO();
        sponsorDTO.setId(sponsor.getId());
        sponsorDTO.setEventId(sponsor.getEvent().getId());
        sponsorDTO.setLogo(sponsor.getLogo());
        sponsorDTO.setName(sponsor.getName());
        sponsorDTO.setPurchasedPackage(sponsor.getPurchasedPackage());
        return sponsorDTO;
    }

    public Sponsor convertToSponsor(SponsorDTO sponsorDTO) {
        Sponsor sponsor = new Sponsor();
        sponsor.setId(sponsorDTO.getId());
        sponsor.setLogo(sponsorDTO.getLogo());
        sponsor.setName(sponsorDTO.getName());
        sponsor.setEvent(eventRepository.getOne(sponsorDTO.getEventId()));
        sponsor.setPurchasedPackage(sponsorDTO.getPurchasedPackage());
        return sponsor;
    }




}
