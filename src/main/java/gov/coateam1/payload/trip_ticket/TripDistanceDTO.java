package gov.coateam1.payload.trip_ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDistanceDTO {


    private Long tripDistanceId;

    @Min(value = 0)
    private Long startDistance;

    @NotNull(message = "speedometer reading at the end of the trip is required!")
    @Min(value = 0)
    private Long endDistance;

    @Min(value = 0)
    private Integer distanceTravelled;
}
