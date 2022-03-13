package ro.societateahermes.backendservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Offer {
    @Id
    @GeneratedValue
    private long offerId;
    private String offerName;
    private String offerDescription;
    private String offerLink;

    @ManyToOne
    @JoinColumn(name = "sponsorId")
    private Sponsor sponsor;

}
