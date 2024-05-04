package gov.coateam1.payload.trip_ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripTimeDTO {


    private Long tripTimeId;

    @NotNull(message = "time departed from office is required!")
    private String timeOfficeDeparture;

    @NotNull(message = "time arrived to destination is required!")
    private String timePlaceArrival;

    @NotNull(message = "time departed from destination is required!")
    private String timePlaceDeparture;

    @NotNull(message = "time arrived to office is required!")
    private String timeOfficeArrival;
}
