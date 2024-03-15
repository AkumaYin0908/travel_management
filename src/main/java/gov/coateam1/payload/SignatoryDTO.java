package gov.coateam1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SignatoryDTO{

    private Long id;
    private String name;
    private String position;
    private boolean active;

    public SignatoryDTO(Long id, String name, String position, boolean active) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.active = active;
    }
}
