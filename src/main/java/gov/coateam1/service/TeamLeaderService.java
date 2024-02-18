package gov.coateam1.service;

import gov.coateam1.payload.SignatoryDTO;

import java.util.List;

public interface TeamLeaderService {


    List<SignatoryDTO> findAll();

    SignatoryDTO findByName(String name);

    SignatoryDTO findById(Long id);

    SignatoryDTO findByActiveStatus(boolean active);

    SignatoryDTO add(SignatoryDTO signatoryDTO) throws Exception;

    SignatoryDTO update(SignatoryDTO signatoryDTO, Long id);

    SignatoryDTO updateByActiveStatus(boolean active, long id);

    void delete(Long id);
}
