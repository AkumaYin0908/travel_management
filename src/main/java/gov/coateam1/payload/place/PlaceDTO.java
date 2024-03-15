package gov.coateam1.payload.place;


import gov.coateam1.model.place.Municipality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {

    private Long id;
    private String buildingName;
    private BarangayDTO barangay;
    private MunicipalityDTO municipality;
    private ProvinceDTO province;
    private RegionDTO regionDTO;
    private String defaultPlace;

}
