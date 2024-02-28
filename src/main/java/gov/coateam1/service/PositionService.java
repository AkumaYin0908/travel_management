package gov.coateam1.service;






import gov.coateam1.payload.PositionDTO;

import java.util.List;

public interface PositionService {

    PositionDTO findByName(String name);

    List<PositionDTO> findAll();

    PositionDTO  add(PositionDTO positionDTO);

    PositionDTO update(PositionDTO positionDTO, Long id);

    void delete(Long id);



}
