package gov.coateam1.mapper;

import gov.coateam1.model.place.Barangay;
import gov.coateam1.model.place.Municipality;
import gov.coateam1.model.place.Place;
import gov.coateam1.model.place.Province;
import gov.coateam1.payload.BasicDTO;
import gov.coateam1.payload.place.BarangayDTO;
import gov.coateam1.payload.place.MunicipalityDTO;
import gov.coateam1.payload.place.PlaceDTO;
import gov.coateam1.payload.place.ProvinceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceMapper {

    public BarangayDTO toBarangayDTO(Barangay barangay){
       return new BarangayDTO(barangay.getId(),barangay.getName());
    }

    public Barangay toBarangay(BasicDTO basicDTO){
        return new Barangay(basicDTO.getId(),basicDTO.getName());
    }

    public MunicipalityDTO toMunicipalityDTO(Municipality municipality){
        return new MunicipalityDTO(municipality.getId(),municipality.getName());
    }

    public Municipality toMunicipality(BasicDTO basicDTO){
        return new Municipality(basicDTO.getId(),basicDTO.getName());
    }

    public ProvinceDTO toProvinceDTO(Province province){
        return new ProvinceDTO(province.getId(),province.getName());
    }

    public Province toProvince(BasicDTO basicDTO){
        return new Province(basicDTO.getId(),basicDTO.getName());
    }





    public Place mapToModel(PlaceDTO placeDTO){
        Barangay barangay=this.toBarangay(placeDTO.getBarangay());
        Municipality municipality=this.toMunicipality(placeDTO.getMunicipality());
        Province province=this.toProvince(placeDTO.getProvince());

        return new Place(placeDTO.getId(), placeDTO.getBuildingName(),
                barangay, municipality, province,placeDTO.getDefaultPlace());
    }

    public PlaceDTO mapToDTO(Place place){
        BarangayDTO barangayDTO = this.toBarangayDTO(place.getBarangay());
        MunicipalityDTO municipalityDTO = this.toMunicipalityDTO(place.getMunicipality());
        ProvinceDTO provinceDTO = this.toProvinceDTO(place.getProvince());

        return new PlaceDTO(place.getId(), place.getBuildingName(),
        barangayDTO,municipalityDTO,provinceDTO, place.getDefaultPlace());
    }

    public void  mapToModel(PlaceDTO placeDTO, Place place){
        Barangay barangay=this.toBarangay(placeDTO.getBarangay());
        Municipality municipality=this.toMunicipality(placeDTO.getMunicipality());
        Province province=this.toProvince(placeDTO.getProvince());

        place.setBuildingName(placeDTO.getBuildingName());
        place.setBarangay(barangay);
        place.setMunicipality(municipality);
        place.setProvince(province);
        place.setDefaultPlace(placeDTO.getDefaultPlace());
    }



}
