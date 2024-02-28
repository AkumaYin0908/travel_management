package gov.coateam1.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripTicketDTO {

    private Long id;

    private EmployeeDTO employeeDTO;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateDeparture;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateReturn;

    private List<PlaceDTO> placeDTOs;

    private List<EmployeeDTO> passengers;

    @JsonFormat(pattern = "h:mm a")
    private LocalTime timeOfficeDeparture;

    @JsonFormat(pattern = "h:mm a")
    private LocalTime timePlaceArrival;

    @JsonFormat(pattern = "h:mm a")
    private LocalTime timePlaceDeparture;

    @JsonFormat(pattern = "h:mm a")
    private LocalTime timeOfficeArrival;

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

    private PurposeDTO purposeDTO;

    private VehicleDTO vehicleDTO;
















}
