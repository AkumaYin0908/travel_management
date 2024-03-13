package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.TripTicket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private Long id;
    private String brand;
    private String model;
    private String type;
    private String plateNo;


    @Override
    public String toString() {
        return String.format("%s %s/%s/%s",
                brand,model,type,plateNo);
    }
}
