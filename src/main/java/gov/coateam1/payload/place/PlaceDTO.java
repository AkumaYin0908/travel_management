package gov.coateam1.payload.place;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDTO {

    private Long id;


    @Getter(AccessLevel.NONE)
    private String buildingName;
    private BarangayDTO barangay;
    private MunicipalityDTO municipality;
    private ProvinceDTO province;
    private RegionDTO region;

    @Getter(AccessLevel.NONE)
    private String defaultPlace;

    public PlaceDTO(Long id, String buildingName, BarangayDTO barangay, MunicipalityDTO municipality, ProvinceDTO province, RegionDTO region, String defaultPlace) {
        this.id = id;
        this.buildingName = buildingName;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.region = region;
        this.defaultPlace = defaultPlace;
    }

    public PlaceDTO(String defaultPlace) {
        this.defaultPlace = defaultPlace;
    }

    public PlaceDTO(Long id, String defaultPlace) {
        this.id = id;
        this.defaultPlace = defaultPlace;
    }

    public String getBuildingName() {
        return buildingName == null ? "N/A" : buildingName;
    }

    public String getDefaultPlace() {
        return defaultPlace == null ? "N/A" : defaultPlace;
    }
}
