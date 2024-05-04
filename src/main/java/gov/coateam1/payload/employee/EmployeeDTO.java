package gov.coateam1.payload.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.payload.PositionDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO{

    private Long id;

    @NotNull(message = "name is required")
    @Size(min = 5, message = "name must not be lower than 5 characters!")
    private String name;

    @JsonIgnore
    private String employeeType;


    @NotNull(message = "position is required!")
    private PositionDTO position;

    public EmployeeDTO(Long id, String name, PositionDTO position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }
}
