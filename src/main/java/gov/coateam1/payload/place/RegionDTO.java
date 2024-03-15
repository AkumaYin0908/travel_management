package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegionDTO extends BasicDTO {

    private String psgcCode;

    public RegionDTO(String code) {
        super(code);
    }

    public RegionDTO(String code, String name, String psgcCode) {
        super(code, name);
        this.psgcCode = psgcCode;
    }
}
