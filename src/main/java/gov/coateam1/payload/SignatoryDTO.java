package gov.coateam1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SignatoryDTO extends BasicDTO{


    private String position;
    private boolean active;

    public SignatoryDTO(Long id, String name, String position, boolean active) {
        super(id, name);
        this.position = position;
        this.active = active;
    }
}
