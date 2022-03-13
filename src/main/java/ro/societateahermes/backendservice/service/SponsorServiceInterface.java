package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.Sponsor;
import ro.societateahermes.backendservice.entities.dto.SponsorDTO;

import java.util.List;

public interface SponsorServiceInterface {

    List<SponsorDTO> getAll();

    void save(SponsorDTO sponsorDTO);

    void delete(long sponsorId);

    void update(SponsorDTO sponsorDTO);

    SponsorDTO getOne(long sponsorId);
}
