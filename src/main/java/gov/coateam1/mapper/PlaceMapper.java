package gov.coateam1.mapper;

import gov.coateam1.model.place.*;
import gov.coateam1.payload.place.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceMapper {

    public BarangayDTO toBarangayDTO(Barangay barangay){
       return barangay == null ? null : new BarangayDTO(barangay.getBrgyCode(),barangay.getBrgyName());
    }

    public Barangay toBarangay(BarangayDTO barangayDTO){

        return barangayDTO==null ? null : new Barangay(barangayDTO.getCode(),barangayDTO.getName());
    }

    public MunicipalityDTO toMunicipalityDTO(Municipality municipality){
        return municipality == null ? null : new MunicipalityDTO(municipality.getMunicipalityCode(),municipality.getMunicipalityName());
    }

    public Municipality toMunicipality(MunicipalityDTO municipalityDTO){
        return municipalityDTO == null ? null : new Municipality(municipalityDTO.getCode(),municipalityDTO.getName());
    }

    public ProvinceDTO toProvinceDTO(Province province){
        return province == null ? null : new ProvinceDTO(province.getProvinceCode(),province.getProvinceName());
    }

    public Province toProvince(ProvinceDTO provinceDTO){
        return provinceDTO == null ? null : new Province(provinceDTO.getCode(),provinceDTO.getName());
    }

    public RegionDTO regionDTO(Region region){
        return region == null ? null : new RegionDTO(region.getRegionCode(),region.getRegionName());
    }

    public Region toRegion(RegionDTO regionDTO){
        return regionDTO == null ? null : new Region(regionDTO.getCode(),regionDTO.getName());
    }





    public Place mapToModel(PlaceDTO placeDTO){
        if(placeDTO.getDefaultPlace()!=null){
            return new Place(placeDTO.getDefaultPlace());
        }

        Barangay barangay=this.toBarangay(placeDTO.getBarangay());
        Municipality municipality=this.toMunicipality(placeDTO.getMunicipality());
        Province province=this.toProvince(placeDTO.getProvince());
        Region region = this.toRegion(placeDTO.getRegion());

        return new Place(placeDTO.getId(), placeDTO.getBuildingName(),
                barangay, municipality, province,region);
    }

    public PlaceDTO mapToDTO(Place place){
        if(place.getDefaultPlace()!=null){
            return new PlaceDTO(place.getDefaultPlace());
        }

        BarangayDTO barangayDTO = this.toBarangayDTO(place.getBarangay());
        MunicipalityDTO municipalityDTO = this.toMunicipalityDTO(place.getMunicipality());
        ProvinceDTO provinceDTO = this.toProvinceDTO(place.getProvince());
        RegionDTO regionDTO = this.regionDTO(place.getRegion());

        return new PlaceDTO(place.getId(), place.getBuildingName(),
        barangayDTO,municipalityDTO,provinceDTO,regionDTO);
    }

    public void  mapToModel(PlaceDTO placeDTO, Place place){

        if(place.getDefaultPlace()!=null){
            place.setDefaultPlace(placeDTO.getDefaultPlace());
            return;
        }

        Barangay barangay=this.toBarangay(placeDTO.getBarangay());
        Municipality municipality=this.toMunicipality(placeDTO.getMunicipality());
        Province province=this.toProvince(placeDTO.getProvince());

        place.setBuildingName(placeDTO.getBuildingName());
        place.setBarangay(barangay);
        place.setMunicipality(municipality);
        place.setProvince(province);

    }



}
