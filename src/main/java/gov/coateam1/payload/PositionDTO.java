package gov.coateam1.payload;

import gov.coateam1.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class PositionDTO extends BasicDTO{

    public PositionDTO(Long id, String name) {
        super(id, name);
    }
}
