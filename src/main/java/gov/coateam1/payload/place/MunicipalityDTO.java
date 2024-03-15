package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class MunicipalityDTO extends BasicDTO {

    private String provinceCode;
    private String psgcCode;
    private String regionCode;

    public MunicipalityDTO(String code) {
        super(code);
    }

    public MunicipalityDTO(String code, String name, String provinceCode, String psgcCode, String regionCode) {
        super(code, name);
        this.provinceCode = provinceCode;
        this.psgcCode = psgcCode;
        this.regionCode = regionCode;
    }
}
