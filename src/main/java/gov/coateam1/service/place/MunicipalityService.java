package gov.coateam1.service.place;


import gov.coateam1.model.place.Municipality;

import java.util.List;

public interface MunicipalityService {

    Municipality findByName(String name);

    List<Municipality> findAll();

    Municipality add(Municipality municipality);

    Municipality update(Municipality municipality);

    void delete(Long id);
}
