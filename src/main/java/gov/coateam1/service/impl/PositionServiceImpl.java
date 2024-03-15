package gov.coateam1.service.impl;


import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.Position;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.repository.PositionRepository;
import gov.coateam1.service.PositionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PositionDTO findByName(String name) {
        Position position =  positionRepository.findByName(name).orElse(new Position(name));
        return modelMapper.map(position,PositionDTO.class);
    }

    @Override
    public List<PositionDTO> findAll() {

        return positionRepository.findAll().stream().map(p->modelMapper.map(p,PositionDTO.class)).toList();
    }

    @Override
    @Transactional
    public PositionDTO add(PositionDTO positionDTO) {
        Position position =   modelMapper.map(positionDTO,Position.class);
        Position dbPosition = positionRepository.save(position);
        positionDTO.setId(dbPosition.getId());
        return modelMapper.map(dbPosition,PositionDTO.class);
    }

    @Override
    @Transactional
    public PositionDTO update(PositionDTO positionDTO, Long id) {
        Position position = positionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Position","id",id));
        position.setName(positionDTO.getName());
        positionRepository.save(position);
        positionDTO.setId(id);
        return positionDTO;
    }

    @Override
    public void delete(Long id) {
        positionRepository.deleteById(id);
    }
}
