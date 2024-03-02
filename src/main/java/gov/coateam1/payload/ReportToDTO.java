package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.TravelOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
public class ReportToDTO extends BasicDTO {

    public ReportToDTO(Long id, String name) {
        super(id, name);
    }
}
