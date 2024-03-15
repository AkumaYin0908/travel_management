package gov.coateam1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasicDTO {

    private String code;
    private String name;


    public BasicDTO(String code) {
        this.code = code;
    }
}
