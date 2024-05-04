package gov.coateam1.payload.trip_ticket;



import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripTicketDTO {

    private Long id;

    private EmployeeDTO employee;

    @NotNull(message = "departure date is required!")
    private String dateDeparture;

    @NotNull(message = "return date is required!")
    private String dateReturn;

    private TripTimeDTO tripTime;

    @NotNull(message = "place/s is required!")
    private Set<PlaceDTO> places;

    @NotNull(message = "passenger/s is required!")
    private Set<EmployeeDTO> passengers;

    private TripFuelDTO tripFuel;

    @Min(value = 0)
    private BigDecimal gearOil;

    @Min(value = 0)
    private BigDecimal lubricantOil;

    private TripDistanceDTO tripDistance;

    private String remarks;

    @NotNull(message = "purpose is required!")
    private PurposeDTO purpose;

    @NotNull(message = "vehicle is required!")
    private VehicleDTO vehicle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripTicketDTO that = (TripTicketDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
