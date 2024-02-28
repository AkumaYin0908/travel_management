package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.coateam1.model.ReportTo;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelOrderDTO {

    private Long id;

    private EmployeeDTO employeeDTO;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateIssued;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateDeparture;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateReturn;

    private PurposeDTO purposeDTO;

    private VehicleDTO vehicleDTO;

    private List<ReportToDTO> reportTos;

    private List<PlaceDTO> placeDTOs;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate lastTravel;




    public void addPlaceDTO(PlaceDTO placeDTO){
        if(placeDTOs == null){
            placeDTOs=new ArrayList<>();
        }

        placeDTOs.add(placeDTO);
    }

    public void addReportTos(ReportToDTO reportToDTO){
        if(reportTos == null){
            reportTos = new ArrayList<>();
        }

        reportTos.add(reportToDTO);
    }



}
