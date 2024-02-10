package gov.coateam1.service;

import gov.coateam1.model.Position;

import java.util.List;

public interface PositionService {

    Position findByName(String name);

    List<Position> findAll();

    Position  add(Position position);

    Position update(Position position);

    void delete(Long id);



}
