package gov.coateam1.service.impl.place;

import gov.coateam1.constants.AppConstant;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.functionalinterface.ThrowFunction;
import gov.coateam1.mapper.PlaceMapper;
import gov.coateam1.model.place.*;
import gov.coateam1.payload.place.*;
import gov.coateam1.repository.place.*;
import gov.coateam1.service.place.PlaceService;
import gov.coateam1.util.JSONDataLoader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final BarangayRepository barangayRepository;
    private final MunicipalityRepository municipalityRepository;
    private final ProvinceRepository provinceRepository;
    private final RegionRepository regionRepository;
    private final JSONDataLoader jsonDataLoader;
    private final PlaceMapper placeMapper;
    private final ModelMapper modelMapper;
    @Override
    public PlaceDTO findById(Long id) throws Exception {

        Place place = placeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Place","id",id));
        return placeMapper.mapToDTO(place);
    }

    @Override
    @Transactional
    public PlaceDTO findPlaceByCodes(PlaceDTO placeDTO) throws Exception {
        Optional<Place> optionalPlace = placeRepository.findPlaceByCodes(
                placeDTO.getBuildingName(),
                placeDTO.getBarangay() == null ? null : placeDTO.getBarangay().getCode(),
                placeDTO.getMunicipality().getCode(),
                placeDTO.getProvince().getCode(),
                placeDTO.getRegion().getCode()
        );
        return placeMapper.mapToDTO(optionalPlace.orElse(constructPlace(placeDTO)));
    }


    @Override
    public PlaceDTO findByDefaultPlace(String defaultPlace) throws Exception {
        Place place =  placeRepository.findByDefaultPlace(defaultPlace).orElse(new Place(defaultPlace));
        return placeMapper.mapToDTO(place);
    }



    @Override
    public List<PlaceDTO> findAll() {
        return placeRepository.findAll().stream()
                .map(ThrowFunction.throwingFunction(placeMapper::mapToDTO)).toList();
    }

    @Override
    @Transactional
    public PlaceDTO add(PlaceDTO placeDTO) throws Exception {
        Place place = placeMapper.mapToModel(findPlaceByCodes(placeDTO));
        Place dbPlace = placeRepository.save(place);
        return placeMapper.mapToDTO(place);
    }

    @Override
    @Transactional
    public PlaceDTO update(PlaceDTO placeDTO, Long id) {
        Place place = placeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Place","id",id));
        modelMapper.map(placeDTO, Place.class);
        placeRepository.save(place);
        placeDTO.setId(id);

        return  placeDTO;
    }

    @Override
    public void delete(Long id) {
        placeRepository.deleteById(id);
    }

    private Place constructPlace(PlaceDTO placeDTO) throws Exception {
        Place place = new Place();
        place.setBuildingName(placeDTO.getBuildingName());
        if (placeDTO.getBarangay() == null) {
            place.setBarangay(null);
        } else {
            BarangayDTO barangayDTO = jsonDataLoader.getFromCode(placeDTO.getBarangay().getCode(), AppConstant.BARANGAY_JSON, BarangayDTO.class);
            Barangay barangay = new Barangay(barangayDTO.getCode(), barangayDTO.getName());
            barangay.addPlace(place);
            barangayRepository.save(barangay);

        }
        MunicipalityDTO municipalityDTO = jsonDataLoader.getFromCode(placeDTO.getMunicipality().getCode(), AppConstant.MUNICIPALITY_JSON, MunicipalityDTO.class);
        Municipality municipality = modelMapper.map(municipalityDTO, Municipality.class);
        municipality.addPlace(place);
        municipalityRepository.save(municipality);

        ProvinceDTO provinceDTO = jsonDataLoader.getFromCode(placeDTO.getProvince().getCode(), AppConstant.PROVINCE_JSON, ProvinceDTO.class);
        Province province = new Province(provinceDTO.getCode(), provinceDTO.getName());
        province.addPlace(place);
        provinceRepository.save(province);

        RegionDTO regionDTO = jsonDataLoader.getFromCode(placeDTO.getRegion().getCode(), AppConstant.REGION_JSON, RegionDTO.class);
        Region region = new Region(regionDTO.getCode(), regionDTO.getName());
        region.addPlace(place);
        regionRepository.save(region);

       return  place;
    }
}
