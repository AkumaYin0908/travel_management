package gov.coateam1.service.impl;

import gov.coateam1.payload.SignatoryDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.signatory.TeamLeader;
import gov.coateam1.repository.TeamLeaderRepository;
import gov.coateam1.service.TeamLeaderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeamLeaderServiceImpl implements TeamLeaderService {

    private final TeamLeaderRepository teamLeaderRepository;
    private final ModelMapper modelMapper;



    @Override
    public List<SignatoryDTO> findAll() {
        return teamLeaderRepository.findAll().stream().map(t->modelMapper.map(t,SignatoryDTO.class)).toList();
    }

    @Override
    public SignatoryDTO findByName(String name) {
        TeamLeader teamLeader = teamLeaderRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("TeamLeader","name",name));
        return modelMapper.map(teamLeader,SignatoryDTO.class);
    }

    @Override
    public SignatoryDTO findById(Long id) {
        TeamLeader teamLeader = teamLeaderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TeamLeader","name",id));
        return modelMapper.map(teamLeader,SignatoryDTO.class);
    }

    @Override
    public SignatoryDTO findByActiveStatus(boolean active) {
        TeamLeader teamLeader = teamLeaderRepository.findByActiveStatus(active).orElseThrow(()-> new ResourceNotFoundException("No Team Leader is currently active!"));
        return modelMapper.map(teamLeader,SignatoryDTO.class);
    }

    @Override
    @Transactional
    public SignatoryDTO add(SignatoryDTO signatoryDTO){
        Optional<TeamLeader> teamLeaderOptional = teamLeaderRepository.findByActiveStatus(true);
        TeamLeader teamLeader = modelMapper.map(signatoryDTO,TeamLeader.class);
        teamLeader.setActive(teamLeaderOptional.isEmpty());
        TeamLeader dbTeamLeader = teamLeaderRepository.save(teamLeader);
        signatoryDTO.setId(dbTeamLeader.getId());
        return signatoryDTO;
    }

    @Override
    @Transactional
    public SignatoryDTO update(SignatoryDTO signatoryDTO, Long id) {
        TeamLeader teamLeader = teamLeaderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TeamLeader","id",id));
        teamLeader.setName(signatoryDTO.getName());
        teamLeader.setPosition(signatoryDTO.getPosition());
        teamLeader.setActive(signatoryDTO.isActive());
        teamLeaderRepository.save(teamLeader);
        signatoryDTO.setId(id);
        return signatoryDTO;
    }

    @Override
    @Transactional
    public SignatoryDTO updateByActiveStatus(boolean active, long id) {
        teamLeaderRepository.updateByActiveStatus(active,id);
        TeamLeader teamLeader = teamLeaderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TeamLeader","id",id));
        return modelMapper.map(teamLeader,SignatoryDTO.class);
    }

    @Override
    public void delete(Long id) {
        teamLeaderRepository.deleteById(id);
    }
}
