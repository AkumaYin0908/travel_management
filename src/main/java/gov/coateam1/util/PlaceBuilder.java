package gov.coateam1.util;

import gov.coateam1.constants.AppConstant;
import gov.coateam1.model.place.*;
import gov.coateam1.payload.place.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceBuilder {

    private final JSONDataLoader jsonDataLoader;

    public Place constructPlace(PlaceDTO placeDTO) throws Exception {
        Place place = new Place();
        place.setBuildingName(placeDTO.getBuildingName());

        if (placeDTO.getBarangay() == null) {
            place.setBarangay(null);
        } else {
            BarangayDTO barangayDTO = jsonDataLoader.getFromCode(placeDTO.getBarangay().getCode(), AppConstant.BARANGAY_JSON, BarangayDTO.class);
            Barangay barangay = new Barangay(barangayDTO.getCode(), barangayDTO.getName());
            barangay.addPlace(place);
        }
        MunicipalityDTO municipalityDTO = jsonDataLoader.getFromCode(placeDTO.getMunicipality().getCode(), AppConstant.MUNICIPALITY_JSON, MunicipalityDTO.class);
        Municipality municipality = new Municipality(municipalityDTO.getCode(), municipalityDTO.getName());
        place.setMunicipality(municipality);

        ProvinceDTO provinceDTO = jsonDataLoader.getFromCode(placeDTO.getProvince().getCode(), AppConstant.PROVINCE_JSON, ProvinceDTO.class);
        Province province = new Province(provinceDTO.getCode(), provinceDTO.getName());
        province.addPlace(place);

        RegionDTO regionDTO = jsonDataLoader.getFromCode(placeDTO.getRegion().getCode(), AppConstant.REGION_JSON, RegionDTO.class);
        Region region = new Region(regionDTO.getCode(), regionDTO.getName());
        region.addPlace(place);

    return place;
    }
}

