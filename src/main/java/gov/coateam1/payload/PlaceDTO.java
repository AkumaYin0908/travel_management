package gov.coateam1.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {

    private Long id;
    private String buildingName;
    private String barangay;
    private String municipality;
    private String province;
    private String defaultPlace;

}
