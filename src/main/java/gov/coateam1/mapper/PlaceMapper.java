package gov.coateam1.mapper;

import gov.coateam1.constants.AppConstant;
import gov.coateam1.model.place.*;
import gov.coateam1.payload.place.*;
import gov.coateam1.util.JSONDataLoader;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceMapper {

    private final ModelMapper modelMapper;
    private final JSONDataLoader jsonDataLoader;


    public Place mapToModel(PlaceDTO placeDTO){
        if(!placeDTO.getDefaultPlace().equals("N/A")){
            return new Place(placeDTO.getId(), placeDTO.getDefaultPlace());
        }

        Barangay barangay= placeDTO.getBarangay() == null ? null : modelMapper.map(placeDTO.getBarangay(),Barangay.class);
        Municipality municipality=placeDTO.getMunicipality() == null ? null : modelMapper.map(placeDTO.getMunicipality(), Municipality.class);
        Province province=placeDTO.getProvince() == null ? null : modelMapper.map(placeDTO.getProvince(), Province.class);
        Region region = placeDTO.getRegion() == null ? null :modelMapper.map(placeDTO.getRegion(),Region.class);

        return new Place(placeDTO.getId(), placeDTO.getBuildingName(),
                barangay, municipality, province,region);
    }

    public PlaceDTO mapToDTO(Place place) throws Exception{
        if(place.getDefaultPlace() != null){
            return new PlaceDTO(place.getId(), place.getDefaultPlace());
        }

        BarangayDTO barangayDTO = place.getBarangay() == null ? null : jsonDataLoader.getFromCode(place.getBarangay().getBrgyCode(), AppConstant.BARANGAY_JSON, BarangayDTO.class);
        MunicipalityDTO municipalityDTO = place.getMunicipality() == null ? null : jsonDataLoader.getFromCode(place.getMunicipality().getMunicipalityCode(),AppConstant.MUNICIPALITY_JSON, MunicipalityDTO.class);
        ProvinceDTO provinceDTO = place.getProvince() == null ? null :  jsonDataLoader.getFromCode(place.getProvince().getProvinceCode(),AppConstant.PROVINCE_JSON, ProvinceDTO.class);
        RegionDTO regionDTO = place.getRegion() == null ? null : jsonDataLoader.getFromCode(place.getRegion().getRegionCode(),AppConstant.REGION_JSON, RegionDTO.class);

        return new PlaceDTO(place.getId(), place.getBuildingName(),
        barangayDTO,municipalityDTO,provinceDTO,regionDTO);
    }

}
