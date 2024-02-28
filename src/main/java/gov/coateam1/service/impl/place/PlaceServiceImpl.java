package gov.coateam1.service.impl.place;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.PlaceMapper;
import gov.coateam1.model.place.Place;
import gov.coateam1.payload.place.PlaceDTO;
import gov.coateam1.repository.place.PlaceRepository;
import gov.coateam1.service.place.PlaceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;
    @Override
    public PlaceDTO findById(Long id) {

        Place place = placeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Place","id",id));

        return placeMapper.mapToDTO(place);
    }

    @Override
    public PlaceDTO findByBarangayName(String barangayName) {
        Place place = placeRepository.findByBarangayName(barangayName).orElseThrow(()->new ResourceNotFoundException("Place","barangay",barangayName));
        return placeMapper.mapToDTO(place);
    }

    @Override
    public PlaceDTO findByMunicipalityName(String municipalityName) {
        Place place =  placeRepository.findByMunicipalityName(municipalityName).orElseThrow(()->new ResourceNotFoundException("Place","municipality",municipalityName));
        return placeMapper.mapToDTO(place);
    }

    @Override
    public PlaceDTO findByProvinceName(String provinceName) {

        Place place = placeRepository.findByProvinceName(provinceName).orElseThrow(()->new ResourceNotFoundException("Place","province",provinceName));
        return  placeMapper.mapToDTO(place);
    }

    @Override
    public PlaceDTO findByBuildingName(String buildingName) {
        Place place = placeRepository.findByBuildingName(buildingName).orElseThrow(()->new ResourceNotFoundException("Place","buildingName",buildingName));
        return placeMapper.mapToDTO(place);
    }

    @Override
    public PlaceDTO findByDefaultPlace(String defaultPlace) {
        Place place =  placeRepository.findByDefaultPlace(defaultPlace).orElse(new Place(defaultPlace));
        return placeMapper.mapToDTO(place);
    }

    @Override
    public PlaceDTO findByCompletePlaceDetails(String buildingName, String barangay, String municipality, String province) {
        Place place = placeRepository.findByCompletePlaceDetails(buildingName,barangay,municipality,province).
                orElseThrow(()->new ResourceNotFoundException(String.format("Place not found with the following:%n " +
                        "buildingName : %s,%n" +
                        "barangay : %s,%n" +
                        "municipality : %s,%n" +
                        "province : %s%n",buildingName,barangay,municipality,province)));

        return placeMapper.mapToDTO(place);
    }

    @Override
    public List<PlaceDTO> findAll() {
        return placeRepository.findAll().stream()
                .map(placeMapper::mapToDTO).toList();
    }

    @Override
    @Transactional
    public PlaceDTO add(PlaceDTO placeDTO) {
        Place place  = placeMapper.mapToModel(placeDTO);
        Place dbPlace = placeRepository.save(place);
        placeDTO.setId(dbPlace.getId());
        return placeDTO;
    }

    @Override
    @Transactional
    public PlaceDTO update(PlaceDTO placeDTO, Long id) {
        Place place = placeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Place","id",id));
        placeMapper.mapToModel(placeDTO,place);
        placeRepository.save(place);
        placeDTO.setId(id);

        return  placeDTO;
    }

    @Override
    public void delete(Long id) {
        placeRepository.deleteById(id);
    }
}
