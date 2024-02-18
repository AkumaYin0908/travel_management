package gov.coateam1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO {

    private int id;
    private String name;
    private List<EmployeeDTO> employees=new ArrayList<>();
}
