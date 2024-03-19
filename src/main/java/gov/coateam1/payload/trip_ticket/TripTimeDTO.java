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
public class TripTimeDTO {


    private Long tripTimeId;

    private String timeOfficeDeparture;

    private String timePlaceArrival;

    private String timePlaceDeparture;

    private String timeOfficeArrival;
}
