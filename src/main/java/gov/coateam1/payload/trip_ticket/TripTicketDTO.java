package gov.coateam1.payload.trip_ticket;



import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripTicketDTO {

    private Long id;

    private EmployeeDTO employee;

    private String dateDeparture;

    private String dateReturn;

    private TripTimeDTO tripTime;

    private Set<PlaceDTO> places;

    private Set<EmployeeDTO> passengers;

    private TripFuelDTO tripFuel;

    private BigDecimal gearOil;

    private BigDecimal lubricantOil;

    private TripDistanceDTO tripDistance;

    private String remarks;

    private PurposeDTO purpose;

    private VehicleDTO vehicle;
















}
