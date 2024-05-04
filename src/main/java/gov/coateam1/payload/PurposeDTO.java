package gov.coateam1.payload;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurposeDTO {

    private Long id;

    @Size(min = 5,message = "purpose should not be less than 5 characters")
    private String purpose;


    public PurposeDTO(String purpose) {
        this.purpose = purpose;
    }
}
