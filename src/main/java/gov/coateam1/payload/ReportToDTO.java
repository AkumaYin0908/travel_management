package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.TravelOrder;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportToDTO{

    private Long id;
    private String name;

}
