package gov.coateam1.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PositionDTO {

    private int id;
    private String name;
    private List<EmployeeDTO> employees=new ArrayList<>();
}
