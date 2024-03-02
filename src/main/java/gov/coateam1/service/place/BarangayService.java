package gov.coateam1.service.place;

import gov.coateam1.model.place.Barangay;
import gov.coateam1.payload.place.BarangayDTO;

import java.util.List;

public interface BarangayService {

    BarangayDTO findByName(String name);

    List< BarangayDTO> findAll();

    BarangayDTO add(BarangayDTO barangayDTO);

    BarangayDTO update (BarangayDTO barangayDTO, Long id);

    void delete(Long id);
}
