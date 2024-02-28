package gov.coateam1.service;

import gov.coateam1.model.Purpose;
import gov.coateam1.payload.PurposeDTO;

import java.util.List;

public interface PurposeService {

    PurposeDTO findByPurpose(String purpose);

    List<PurposeDTO> findAll();

    PurposeDTO add(PurposeDTO purposeDTO);

    PurposeDTO update(PurposeDTO purposeDTO, Long id);

    void delete(Long id);
}
