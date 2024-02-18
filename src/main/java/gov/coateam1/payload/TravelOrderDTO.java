package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.ReportTo;
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
    private String employeeName;

    @JsonFormat(pattern = "MMMM/dd/yyyy")
    private LocalDate dateIssued;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateDeparture;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateReturn;

    private String purpose;

    private String plateNo;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate lastTravel;

    private List<ReportTo> reportTos;


    private List<PlaceDTO> placeDTOs;




    public void addPlaceDTO(PlaceDTO placeDTO){
        if(placeDTOs == null){
            placeDTOs=new ArrayList<>();
        }

        placeDTOs.add(placeDTO);
    }

    public void addReportTos(ReportTo reportTo){
        if(reportTos == null){
            reportTos = new ArrayList<>();
        }

        reportTos.add(reportTo);
    }



}
