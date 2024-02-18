package gov.coateam1.service.place;

import gov.coateam1.model.place.Place;
import gov.coateam1.payload.PlaceDTO;

import java.util.List;

public interface PlaceService {

    PlaceDTO findById(Long id);

    PlaceDTO findByBarangayName(String barangayName);

    PlaceDTO findByMunicipalityName(String municipalityName);

    PlaceDTO findByProvinceName(String provinceName);

    PlaceDTO findByBuildingName(String buildingName);

    PlaceDTO findByDefaultPlace(String defaultPlace);

    PlaceDTO findByCompletePlaceDetails(String buildingName, String barangay,
                                                                  String municipality, String province);

    List<PlaceDTO> findAll();

    PlaceDTO add(PlaceDTO placeDTO);

    PlaceDTO update(PlaceDTO placeDTO, Long id);

    void delete(Long id);


}
