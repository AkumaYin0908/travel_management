package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
public class RegionDTO extends BasicDTO {



    public RegionDTO(String code) {
        super(code);
    }

    public RegionDTO(String code, String name) {
        super(code, name);
    }
}
