package gov.coateam1.service.place;

import gov.coateam1.model.place.Barangay;

import java.util.List;

public interface BarangayService {

    Barangay findByName(String name);

    List<Barangay> findAllBarangays();

    Barangay addBarangay(Barangay barangay);

    Barangay updateBarangay(Barangay barangay);

    void deleteBarangay(Long id);
}
