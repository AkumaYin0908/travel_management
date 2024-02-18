package gov.coateam1.payload;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.TripTicket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurposeDTO {

    private Long id;
    private String purpose;

    @JsonIgnore
    private List<TravelOrderDTO> travelOrders;

    @JsonIgnore
    private List<TripTicketDTO> tripTickets;

    public PurposeDTO(Long id, String purpose) {
        this.id = id;
        this.purpose = purpose;
    }


}
