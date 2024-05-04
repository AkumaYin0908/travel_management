package gov.coateam1.payload;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO{

    private Long id;

    @NotNull(message = "position title is required")
    @Size(min = 5, message = "position title must not be lower than 5 characters!")
    private String name;


}
