package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarangayDTO extends BasicDTO {

    private String cityCode;
    private String provinceCode;


    public BarangayDTO(String code) {
        super(code);
    }

    public BarangayDTO(String code, String name, String cityCode, String provinceCode) {
        super(code, name);
        this.cityCode = cityCode;
        this.provinceCode = provinceCode;
    }
}
