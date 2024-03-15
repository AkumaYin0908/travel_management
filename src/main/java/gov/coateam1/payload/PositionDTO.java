package gov.coateam1.payload;

import gov.coateam1.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO{

    private Long id;
    private String name;


}
