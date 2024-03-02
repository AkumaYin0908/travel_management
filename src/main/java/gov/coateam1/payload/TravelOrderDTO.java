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

    private EmployeeDTO employee;


    private String dateIssued;

    private String dateDeparture;

    private String dateReturn;

    private PurposeDTO purposes;

    private VehicleDTO vehicle;

    private List<ReportToDTO> reportTos;

    private List<PlaceDTO> places;


    private String lastTravel;




    public void addPlaceDTO(PlaceDTO placeDTO){
        if(places == null){
            places=new ArrayList<>();
        }

        places.add(placeDTO);
    }

    public void addReportTos(ReportToDTO reportToDTO){
        if(reportTos == null){
            reportTos = new ArrayList<>();
        }

        reportTos.add(reportToDTO);
    }



}
