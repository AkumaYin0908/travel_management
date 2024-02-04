package gov.coateam1.service;

import gov.coateam1.model.Position;

public interface PositionService {

    Position findPositionByName(String name);

    Position  addPosition(Position position);

    Position updatePosition(Position position);

    void deletePosition(Long id);



}
