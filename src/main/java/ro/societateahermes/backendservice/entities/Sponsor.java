package ro.societateahermes.backendservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sponsor {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String logo;
    @Enumerated(EnumType.STRING)
    private PurchasedPackage purchasedPackage;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "sponsor")
    private List<Offer> offerList;

}
