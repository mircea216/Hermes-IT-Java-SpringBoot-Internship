package ro.societateahermes.backendservice.service.serviceImplementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.Sponsor;
import ro.societateahermes.backendservice.entities.dto.SponsorDTO;
import ro.societateahermes.backendservice.entities.dto.UserDTO;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;
import ro.societateahermes.backendservice.repository.SponsorRepositoryInterface;
import ro.societateahermes.backendservice.service.SponsorServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.SponsorMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SponsorServiceImplementation implements SponsorServiceInterface {

    private SponsorRepositoryInterface sponsorRepository;

    @Autowired
    private SponsorMapper sponsorMapper;

    public SponsorServiceImplementation(SponsorRepositoryInterface sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }


    @Override
    public List<SponsorDTO> getAll() {
        return sponsorRepository.findAll().stream().map(sponsor -> sponsorMapper.convertToDTO(sponsor))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SponsorDTO sponsorDTO) {
        sponsorRepository.save(sponsorMapper.convertToSponsor(sponsorDTO));
    }

    @Override
    public void delete(long sponsorId) {
        sponsorRepository.delete(sponsorRepository.findById(sponsorId).orElseThrow());

    }

    @Override
    public void update(SponsorDTO sponsorDTO) {
        Sponsor sponsor = sponsorMapper.convertToSponsor(sponsorDTO);
        sponsorRepository.save(sponsor);
    }

    @Override
    public SponsorDTO getOne(long sponsorId) {
        return sponsorMapper.convertToDTO(sponsorRepository.findById(sponsorId).orElseThrow());
    }

}
