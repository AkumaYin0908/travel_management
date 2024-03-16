package gov.coateam1.payload;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.TripTicket;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurposeDTO {

    private Long id;
    private String purpose;


    public PurposeDTO(String purpose) {
        this.purpose = purpose;
    }
}
