package gov.coateam1.service.place;

import gov.coateam1.model.place.Province;

import java.util.List;

public interface ProvinceService {

    Province findByName(String name);

    List<Province> getAll();

    Province add(Province province);

    Province update(Province province);

    void delete(Long id);

}
