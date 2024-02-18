package gov.coateam1.mapper;

import gov.coateam1.model.place.Barangay;
import gov.coateam1.model.place.Municipality;
import gov.coateam1.model.place.Place;
import gov.coateam1.model.place.Province;
import gov.coateam1.payload.PlaceDTO;
import gov.coateam1.service.place.BarangayService;
import gov.coateam1.service.place.MunicipalityService;
import gov.coateam1.service.place.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceMapper {

    private final BarangayService barangayService;
    private final MunicipalityService municipalityService;
    private final ProvinceService provinceService;

    public Place mapToModel(PlaceDTO placeDTO){
        Barangay barangay = placeDTO.getBarangay().isEmpty()? null : barangayService.findByName(placeDTO.getBarangay());
        Municipality municipality = placeDTO.getMunicipality().isEmpty() ? null :  municipalityService.findByName(placeDTO.getMunicipality());
        Province province = placeDTO.getProvince().isEmpty() ? null : provinceService.findByName(placeDTO.getProvince());

        return new Place(placeDTO.getId(), placeDTO.getBuildingName(),
                barangay, municipality, province,placeDTO.getDefaultPlace());
    }

    public PlaceDTO mapToDTO(Place place){
        String barangayName = place.getBarangay() == null ? "" : place.getBarangay().getName();
        String municipalityName = place.getMunicipality() == null ? "" : place.getMunicipality().getName();
        String provinceName = place.getProvince() == null ? "" : place.getProvince().getName();

        return new PlaceDTO(place.getId(), place.getBuildingName(),
        barangayName,municipalityName,provinceName, place.getDefaultPlace());
    }

    public void  mapToModel(PlaceDTO placeDTO, Place place){
        Barangay barangay = placeDTO.getBarangay().isEmpty()? null : barangayService.findByName(placeDTO.getBarangay());
        Municipality municipality = placeDTO.getMunicipality().isEmpty() ? null :  municipalityService.findByName(placeDTO.getMunicipality());
        Province province = placeDTO.getProvince().isEmpty() ? null : provinceService.findByName(placeDTO.getProvince());

        place.setBuildingName(placeDTO.getBuildingName());
        place.setBarangay(barangay);
        place.setMunicipality(municipality);
        place.setProvince(province);
        place.setDefaultPlace(placeDTO.getDefaultPlace());
    }



}
