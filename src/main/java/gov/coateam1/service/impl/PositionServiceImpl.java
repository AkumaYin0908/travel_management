package gov.coateam1.service.impl;

import gov.coateam1.model.Position;
import gov.coateam1.repository.PositionRepository;
import gov.coateam1.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Override
    public Position findByName(String name) {
        return positionRepository.findByName(name).orElse(new Position(name));
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position add(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Position update(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public void delete(Long id) {
        positionRepository.deleteById(id);
    }
}
