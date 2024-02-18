package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.TravelOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportToDTO {


    private Long id;
    private String name;

    @JsonIgnore
    private List<TravelOrderDTO> travelOrders;

    public ReportToDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
