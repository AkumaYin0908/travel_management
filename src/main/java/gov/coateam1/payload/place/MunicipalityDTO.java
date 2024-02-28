package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MunicipalityDTO extends BasicDTO {

    public MunicipalityDTO(Long id, String name) {
        super(id, name);
    }
}
