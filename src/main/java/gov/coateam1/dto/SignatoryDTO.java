package gov.coateam1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignatoryDTO {

    private Long id;
    private String name;
    private String position;
    private boolean active;

}
