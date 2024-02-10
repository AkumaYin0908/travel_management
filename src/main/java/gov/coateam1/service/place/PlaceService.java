package gov.coateam1.service.place;

import gov.coateam1.exception.PlaceNotFoundException;
import gov.coateam1.model.place.Barangay;
import gov.coateam1.model.place.Municipality;
import gov.coateam1.model.place.Place;
import gov.coateam1.model.place.Province;

import java.util.List;

public interface PlaceService {

    Place findByBarangay(Barangay barangay) throws PlaceNotFoundException;

    Place findByMunicipality(Municipality municipality)throws PlaceNotFoundException;

    Place findByProvince(Province province) throws PlaceNotFoundException;

    Place findByBuildingName(String buildingName) throws PlaceNotFoundException;

    Place findByDefaultPlace(String defaultPlace)throws PlaceNotFoundException;

    List<Place> findAll();

    Place add(Place place);

    Place update(Place place);

    void delete(Long id);


}
