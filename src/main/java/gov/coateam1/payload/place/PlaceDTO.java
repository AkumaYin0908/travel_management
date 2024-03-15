package gov.coateam1.payload.place;


import gov.coateam1.model.place.Municipality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceDTO {

    private Long id;
    private String buildingName;
    private BarangayDTO barangay;
    private MunicipalityDTO municipality;
    private ProvinceDTO province;
    private RegionDTO regionDTO;
    private String defaultPlace;

    public PlaceDTO(Long id, String buildingName, BarangayDTO barangay, MunicipalityDTO municipality, ProvinceDTO province, RegionDTO regionDTO) {
        this.id = id;
        this.buildingName = buildingName;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.regionDTO = regionDTO;
    }

    public PlaceDTO(String defaultPlace) {
        this.defaultPlace = defaultPlace;
    }
}
