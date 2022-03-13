package ro.societateahermes.backendservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.societateahermes.backendservice.entities.PurchasedPackage;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsorDTO {

    private long id;

    @NotNull(message = "Name is mandatory")
    private String name;

    private String logo;

    @NotNull(message = "Package is mandatory")
    private PurchasedPackage purchasedPackage;

    @NotNull(message = "EventId is mandatory")
    private long eventId;
}
