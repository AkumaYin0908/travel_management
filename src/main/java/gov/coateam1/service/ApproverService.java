package gov.coateam1.service;


import gov.coateam1.dto.SignatoryDTO;

import java.util.List;


public interface ApproverService {


    List<SignatoryDTO> findAll();

    SignatoryDTO findByActiveStatus(boolean active);

    SignatoryDTO add(SignatoryDTO signatoryDTO) throws Exception;


    void delete(Long id);


    SignatoryDTO update(SignatoryDTO signatoryDTO, Long id);

    SignatoryDTO updateByActiveStatus(boolean active, Long id);

    SignatoryDTO findByName(String name);
}
