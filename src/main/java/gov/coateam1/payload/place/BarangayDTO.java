package gov.coateam1.payload.place;

import gov.coateam1.payload.BasicDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BarangayDTO extends BasicDTO {

    private String cityCode;

    public BarangayDTO(String code) {
        super(code);
    }

    public BarangayDTO(String code, String name) {
        super(code, name);
    }

    public BarangayDTO(String code, String name, String cityCode) {
        super(code, name);
        this.cityCode = cityCode;
    }
}
