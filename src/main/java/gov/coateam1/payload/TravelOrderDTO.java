package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.coateam1.model.ReportTo;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TravelOrderDTO {

    private Long id;

    private EmployeeDTO employee;

    @NotNull(message = "issued date is required!")
    private String dateIssued;

    @NotNull(message = "departure date is required!")
    private String dateDeparture;

    @NotNull(message = "return date is required!")
    private String dateReturn;

    @NotNull(message = "purpose is required!")
    private PurposeDTO purpose;

    @NotNull(message = "vehicle is required!")
    private VehicleDTO vehicle;

    @NotNull(message = "report/s to is required!")
    private Set<ReportToDTO> reportTos;

    @NotNull(message = "place/s is required!")
    private Set<PlaceDTO> places;

    @NotNull(message = "date of last travel is required!")
    private String lastTravel;


    public TravelOrderDTO(Long id, EmployeeDTO employee, String dateIssued, String dateDeparture, String dateReturn, PurposeDTO purpose, VehicleDTO vehicle, Set<ReportToDTO> reportTos, Set<PlaceDTO> places, String lastTravel) {
        this.id = id;
        this.employee = employee;
        this.dateIssued = dateIssued;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.purpose = purpose;
        this.vehicle = vehicle;
        this.reportTos = reportTos;
        this.places = places;
        this.lastTravel = lastTravel;
    }
}
