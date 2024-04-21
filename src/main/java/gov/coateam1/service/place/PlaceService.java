package gov.coateam1.service.place;

import gov.coateam1.model.place.Place;
import gov.coateam1.payload.place.PlaceDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceService {

    PlaceDTO findById(Long id) throws Exception;

    PlaceDTO findPlaceByCodes(PlaceDTO placeDTO) throws Exception;

    PlaceDTO findByDefaultPlace(String defaultPlace) throws Exception;


    List<PlaceDTO> findAll();

    PlaceDTO add(PlaceDTO placeDTO) throws Exception;

    PlaceDTO update(PlaceDTO placeDTO, Long id);

    void delete(Long id);


}
