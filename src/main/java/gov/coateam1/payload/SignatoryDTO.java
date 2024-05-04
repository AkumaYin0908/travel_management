package gov.coateam1.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class SignatoryDTO{

    private Long id;

    @NotNull(message = "name is required!")
    @Size(min = 5,message = "purpose should not be less than 5 characters")
    private String name;

    @NotNull(message = "position is required!")
    @Size(min = 5,message = "position should not be less than 5 characters")
    private String position;

    private boolean active;

    public SignatoryDTO(Long id, String name, String position, boolean active) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.active = active;
    }
}
