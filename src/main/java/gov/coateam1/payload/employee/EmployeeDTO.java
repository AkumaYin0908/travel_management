package gov.coateam1.payload.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.payload.PositionDTO;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO{

    private Long id;
    private String name;

    @JsonIgnore
    private String employeeType;

    private PositionDTO position;

}
