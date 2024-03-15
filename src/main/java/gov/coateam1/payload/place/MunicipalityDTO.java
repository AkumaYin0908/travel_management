package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class MunicipalityDTO extends BasicDTO {

    private String provinceCode;


    public MunicipalityDTO(String code) {
        super(code);
    }

    public MunicipalityDTO(String code, String name) {
        super(code, name);
    }

    public MunicipalityDTO(String code, String name, String provinceCode) {
        super(code, name);
        this.provinceCode = provinceCode;

    }
}
