package ro.societateahermes.backendservice.utils.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.societateahermes.backendservice.entities.Offer;
import ro.societateahermes.backendservice.entities.Sponsor;
import ro.societateahermes.backendservice.entities.dto.OfferDTO;
import ro.societateahermes.backendservice.repository.EventRepositoryInterface;
import ro.societateahermes.backendservice.repository.SponsorRepositoryInterface;

@Component
public class OfferMapper {

    @Autowired
    private SponsorRepositoryInterface sponsorRepository;

    public OfferDTO convertToDTO(Offer offer) {
        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setOfferId(offer.getOfferId());
        offerDTO.setOfferName(offer.getOfferName());
        offerDTO.setOfferDescription(offer.getOfferDescription());
        offerDTO.setOfferLink(offer.getOfferLink());
        offerDTO.setSponsorId(offer.getSponsor().getId());

        return offerDTO;
    }

    public Offer convertToOffer(OfferDTO offerDTO) {
        Offer offer = new Offer();
        offer.setOfferId(offerDTO.getOfferId());
        offer.setOfferName(offerDTO.getOfferName());
        offer.setOfferDescription(offerDTO.getOfferDescription());
        offer.setOfferLink(offerDTO.getOfferLink());
        Sponsor sponsor = sponsorRepository.getOne(offerDTO.getSponsorId());
        offer.setSponsor(sponsor);

        return offer;
    }
}
