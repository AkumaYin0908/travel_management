package gov.coateam1.service.place;

import gov.coateam1.model.place.Province;
import gov.coateam1.payload.place.ProvinceDTO;

import java.util.List;

public interface ProvinceService {

    ProvinceDTO findByName(String name);

    List<ProvinceDTO> getAll();

    ProvinceDTO add(ProvinceDTO provinceDTO);

    ProvinceDTO update(ProvinceDTO provinceDTO, Long id);

    void delete(Long id);

}
