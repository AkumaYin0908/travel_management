package gov.coateam1.service;

import gov.coateam1.model.Purpose;

import java.util.List;

public interface PurposeService {

    Purpose findByPurpose(String purpose);

    List<Purpose> findAll();

    Purpose save(Purpose purpose);

    Purpose update(Purpose purpose);

    void delete(Long id);
}
