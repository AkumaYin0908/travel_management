package gov.coateam1.service.impl;


import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.PositionMapper;
import gov.coateam1.model.Position;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.repository.PositionRepository;
import gov.coateam1.service.PositionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    @Override
    @Transactional
    public PositionDTO findByName(String name) {
        Position position =  positionRepository.findByName(name).orElse(new Position(name));
        return positionMapper.mapToDTO(position);
    }

    @Override
    public List<PositionDTO> findAll() {

        return positionRepository.findAll().stream().map(positionMapper::mapToDTO).toList();
    }

    @Override
    @Transactional
    public PositionDTO add(PositionDTO positionDTO) {
        Position position =   positionMapper.mapToModel(positionDTO);
        Position dbPosition = positionRepository.save(position);
        positionDTO.setId(dbPosition.getId());
        return positionMapper.mapToDTO(dbPosition);
    }

    @Override
    @Transactional
    public PositionDTO update(PositionDTO positionDTO, Long id) {
        Position position = positionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Position","id",id));
        positionMapper.mapToModel(positionDTO,position);
        positionRepository.save(position);
        positionDTO.setId(id);
        return positionDTO;
    }

    @Override
    public void delete(Long id) {
        positionRepository.deleteById(id);
    }
}
