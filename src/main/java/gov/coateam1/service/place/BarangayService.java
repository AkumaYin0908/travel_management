package gov.coateam1.service.place;

import gov.coateam1.model.place.Barangay;

import java.util.List;

public interface BarangayService {

    Barangay findByName(String name);

    List<Barangay> findAll();

    Barangay add(Barangay barangay);

    Barangay update(Barangay barangay);

    void delete(Long id);
}
