package gov.coateam1.service.place;


import gov.coateam1.model.place.Municipality;
import gov.coateam1.payload.place.MunicipalityDTO;

import java.util.List;

public interface MunicipalityService {

    MunicipalityDTO findByName(String name);

    List<MunicipalityDTO> findAll();

    MunicipalityDTO add(MunicipalityDTO municipalityDTO);

    MunicipalityDTO  update(MunicipalityDTO municipalityDTO,Long id);

    void delete(Long id);
}
