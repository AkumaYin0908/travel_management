package gov.coateam1.payload.trip_ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripFuelDTO {


    private Long tripFuelId;

    @Min(value = 0)
    private BigDecimal fuelBalance;

    @Min(value = 0)
    private BigDecimal issuedFuel;

    @Min(value = 0)
    private BigDecimal purchasedFuel;

    @NotNull(message = "fuel consumed is required!")
    @Min(value = 0)
    private BigDecimal consumedFuel;

    @Min(value = 0)
    private BigDecimal remainingFuel;
}
