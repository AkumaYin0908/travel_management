package gov.coateam1.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasicDTO {

    private String code;
    private String name;


    public BasicDTO(String code) {
        this.code = code;
    }
}
