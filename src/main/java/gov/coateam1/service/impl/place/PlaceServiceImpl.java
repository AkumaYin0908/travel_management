package gov.coateam1.service.impl.place;

import gov.coateam1.exception.PlaceNotFoundException;
import gov.coateam1.model.place.Place;
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
    @Override
    public Place findById(Long id) {
        return placeRepository.findById(id).orElseThrow(()->new PlaceNotFoundException("Place not found!"));
    }

    @Override
    public Place findByBarangayName(String barangayName) {
        return placeRepository.findByBarangayName(barangayName);
    }

    @Override
    public Place findByMunicipalityName(String municipalityName) {
        return placeRepository.findByMunicipalityName(municipalityName);
    }

    @Override
    public Place findByProvinceName(String provinceName) {
        return placeRepository.findByProvinceName(provinceName);
    }

    @Override
    public Place findByBuildingName(String buildingName) {
        return placeRepository.findByBuildingName(buildingName);
    }

    @Override
    public Place findByDefaultPlace(String defaultPlace) {
        return placeRepository.findByDefaultPlace(defaultPlace).orElse(new Place(defaultPlace));
    }

    @Override
    public Place findByCompletePlaceDetails(String buildingName, String barangay, String municipality, String province) {
        return placeRepository.findByCompletePlaceDetails(buildingName,barangay,municipality,province);
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    @Transactional
    public Place add(Place place) {
        return placeRepository.save(place);
    }

    @Override
    @Transactional
    public Place update(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public void delete(Long id) {
        placeRepository.deleteById(id);
    }
}
