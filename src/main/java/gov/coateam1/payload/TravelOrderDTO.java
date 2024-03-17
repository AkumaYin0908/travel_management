package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.coateam1.model.ReportTo;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
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

    private String dateIssued;

    private String dateDeparture;

    private String dateReturn;

    private PurposeDTO purpose;

    private VehicleDTO vehicle;

    private Set<ReportToDTO> reportTos;

    private Set<PlaceDTO> places;

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
