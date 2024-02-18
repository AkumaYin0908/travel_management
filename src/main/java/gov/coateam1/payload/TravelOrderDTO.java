package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<ReportToDTO> reportToDTOs;

    @JsonIgnore
    private List<PlaceDTO> placeDTOs;

    public TravelOrderDTO(Long id, String employeeName, LocalDate dateIssued, LocalDate dateDeparture, LocalDate dateReturn, String purpose, String plateNo) {
        this.id = id;
        this.employeeName = employeeName;
        this.dateIssued = dateIssued;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.purpose = purpose;
        this.plateNo = plateNo;
    }

    public void addPlaceDTO(PlaceDTO placeDTO){
        if(placeDTOs == null){
            placeDTOs=new ArrayList<>();
        }

        placeDTOs.add(placeDTO);
    }

    public void addReportTos(ReportToDTO reportToDTO){
        if(reportToDTOs == null){
            reportToDTOs = new ArrayList<>();
        }

        reportToDTOs.add(reportToDTO);
    }



}
