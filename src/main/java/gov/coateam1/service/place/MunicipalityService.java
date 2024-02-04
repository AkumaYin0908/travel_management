package gov.coateam1.service.place;


import gov.coateam1.model.place.Municipality;

import java.util.List;

public interface MunicipalityService {

    Municipality findByName(String name);

    List<Municipality> findAllMunicipality();

    Municipality addMunicipality(Municipality municipality);

    Municipality updateMunicipality(Municipality municipality);

    void deleteMunicipality(Long id);
}
