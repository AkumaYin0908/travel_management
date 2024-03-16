package gov.coateam1.payload;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class SignatoryDTO{

    private Long id;
    private String name;
    private String position;
    private boolean active;

    public SignatoryDTO(Long id, String name, String position, boolean active) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.active = active;
    }
}
