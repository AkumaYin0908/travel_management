package gov.coateam1.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<EmployeeDTO> employees;

    public PositionDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
