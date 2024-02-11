package gov.coateam1.service.place;

import gov.coateam1.exception.PlaceNotFoundException;
import gov.coateam1.model.place.Barangay;
import gov.coateam1.model.place.Municipality;
import gov.coateam1.model.place.Place;
import gov.coateam1.model.place.Province;

import java.util.List;

public interface PlaceService {

    Place findById(Long id);

    Place findByBarangayName(String barangayName);

    Place findByMunicipalityName(String municipalityName);

    Place findByProvinceName(String provinceName);

    Place findByBuildingName(String buildingName);

    Place findByDefaultPlace(String defaultPlace);

    Place findByCompletePlaceDetails(String buildingName, String barangay,
                                                                  String municipality, String province);

    List<Place> findAll();

    Place add(Place place);

    Place update(Place place);

    void delete(Long id);


}
