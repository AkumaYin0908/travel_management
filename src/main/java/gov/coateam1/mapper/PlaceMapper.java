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
        if(placeDTO.getDefaultPlace()!=null){
            return new Place(placeDTO.getDefaultPlace());
        }

        Barangay barangay= modelMapper.map(placeDTO.getBarangay(),Barangay.class);
        Municipality municipality=modelMapper.map(placeDTO.getMunicipality(), Municipality.class);
        Province province=modelMapper.map(placeDTO.getProvince(), Province.class);
        Region region = modelMapper.map(placeDTO.getRegion(),Region.class);

        return new Place(placeDTO.getId(), placeDTO.getBuildingName(),
                barangay, municipality, province,region);
    }

    public PlaceDTO mapToDTO(Place place) throws Exception{
        if(place.getDefaultPlace()!=null){
            return new PlaceDTO(place.getDefaultPlace());
        }

        BarangayDTO barangayDTO = jsonDataLoader.getFromCode(place.getBarangay().getBrgyCode(), AppConstant.BARANGAY_JSON, BarangayDTO.class);
        MunicipalityDTO municipalityDTO = jsonDataLoader.getFromCode(place.getMunicipality().getMunicipalityCode(),AppConstant.MUNICIPALITY_JSON, MunicipalityDTO.class);
        ProvinceDTO provinceDTO = jsonDataLoader.getFromCode(place.getProvince().getProvinceCode(),AppConstant.PROVINCE_JSON, ProvinceDTO.class);
        RegionDTO regionDTO = jsonDataLoader.getFromCode(place.getRegion().getRegionCode(),AppConstant.REGION_JSON, RegionDTO.class);

        return new PlaceDTO(place.getId(), place.getBuildingName(),
        barangayDTO,municipalityDTO,provinceDTO,regionDTO);
    }

}
