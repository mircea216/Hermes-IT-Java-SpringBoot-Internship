package ro.societateahermes.backendservice.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ro.societateahermes.backendservice.entities.Offer;
import ro.societateahermes.backendservice.entities.dto.OfferDTO;
import ro.societateahermes.backendservice.repository.OfferRepositoryInterface;
import ro.societateahermes.backendservice.service.OfferServiceInterface;
import ro.societateahermes.backendservice.utils.mapper.OfferMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class OfferServiceImplementation implements OfferServiceInterface {
    private OfferMapper offerMapper;
    private OfferRepositoryInterface offerRepository;

    @Autowired
    public OfferServiceImplementation(OfferRepositoryInterface offerRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
    }

    @Override
    public List<OfferDTO> getAll() {
        return offerRepository.findAll().stream().map(offer -> offerMapper.convertToDTO(offer)).collect(Collectors.toList());
    }

    @Override
    public void save(OfferDTO offerDTO) {
        offerRepository.save(offerMapper.convertToOffer(offerDTO));
    }

    @Override
    public void delete(Long offerId) {
        offerRepository.delete(offerRepository.findById(offerId).orElseThrow());

    }

    @Override
    public void update(OfferDTO offerDTO) {
        Offer offer = offerMapper.convertToOffer(offerDTO);
        offerRepository.save(offer);
    }

    @Override
    public OfferDTO getOne(long offerId) {
        return offerMapper.convertToDTO(offerRepository.findById(offerId).orElseThrow());
    }

    @Override
    public List<OfferDTO> getBySponsor(long sponsorId) {
        List<Offer> offerList = new ArrayList<>();
        for (Offer o : offerRepository.findAll()) {
            if (o.getSponsor().getId() == sponsorId)
                offerList.add(o);
        }

        return offerList.stream().map(offer -> offerMapper.convertToDTO(offer)).collect(Collectors.toList());
    }
}
