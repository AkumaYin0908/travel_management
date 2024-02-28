package gov.coateam1.payload.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.TripTicket;
import gov.coateam1.payload.TravelOrderDTO;
import gov.coateam1.payload.TripTicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO{

    private Long id;
    private String name;
    private String position;

    @JsonIgnore
    private List<TravelOrderDTO> travelOrders;
    @JsonIgnore
    private List<TripTicketDTO> tripTickets;


    public EmployeeDTO(Long id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }
}
