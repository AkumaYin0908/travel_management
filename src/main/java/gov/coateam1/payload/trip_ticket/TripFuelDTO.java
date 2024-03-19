package gov.coateam1.payload.trip_ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private BigDecimal fuelBalance;

    private BigDecimal issuedFuel;

    private BigDecimal purchasedFuel;

    private BigDecimal consumedFuel;

    private BigDecimal remainingFuel;
}
