package gov.coateam1.payload;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurposeDTO {

    private Long id;
    private String purpose;


    public PurposeDTO(String purpose) {
        this.purpose = purpose;
    }
}
