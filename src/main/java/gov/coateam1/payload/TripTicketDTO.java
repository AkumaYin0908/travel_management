package gov.coateam1.payload;



import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripTicketDTO {

    private Long id;

    private EmployeeDTO employee;

    private String dateDeparture;

    private String dateReturn;

    private List<PlaceDTO> places;

    private List<EmployeeDTO> passengers;

    private String timeOfficeDeparture;

    private String timePlaceArrival;

    private String timePlaceDeparture;

    private String timeOfficeArrival;

    private BigDecimal fuelBalance;

    private BigDecimal issuedFuel;

    private BigDecimal purchasedFuel;

    private BigDecimal consumedFuel;

    private BigDecimal remainingFuel;

    private BigDecimal gearOil;

    private BigDecimal lubricantOil;

    private Long startDistance;

    private Long endDistance;

    private Integer distanceTravelled;

    private String remarks;

    private PurposeDTO purpose;

    private VehicleDTO vehicle;
















}
