package ro.societateahermes.backendservice.service;

import ro.societateahermes.backendservice.entities.dto.OfferDTO;

import java.util.List;

public interface OfferServiceInterface {
    List<OfferDTO> getAll();

    void save(OfferDTO offerDTO);

    void delete(Long offerId);

    void update(OfferDTO offerDTO);

    OfferDTO getOne(long offerId);

    List<OfferDTO> getBySponsor(long sponsorId);
}
