package ro.societateahermes.backendservice.entities.dto;

import lombok.Data;

@Data
public class OfferDTO {

    private long offerId;
    private String offerName;
    private String offerDescription;
    private String offerLink;
    private long sponsorId;
}
