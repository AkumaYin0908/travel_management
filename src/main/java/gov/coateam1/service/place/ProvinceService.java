package gov.coateam1.service.place;

import gov.coateam1.model.place.Province;

import java.util.List;

public interface ProvinceService {

    Province findByName(String name);

    List<Province> getAllProvince();

    Province addProvince(Province province);

    Province updateProvince(Province province);

    void deleteProvince(Long id);

}
