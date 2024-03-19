package gov.coateam1.payload.trip_ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDistanceDTO {

    @JsonIgnore
    private Long tripDistanceId;

    private Long startDistance;

    private Long endDistance;

    private Integer distanceTravelled;
}
