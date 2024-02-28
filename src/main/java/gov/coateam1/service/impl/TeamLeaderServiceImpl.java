package gov.coateam1.service.impl;

import gov.coateam1.payload.SignatoryDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.SignatoryMapper;
import gov.coateam1.model.signatory.TeamLeader;
import gov.coateam1.repository.TeamLeaderRepository;
import gov.coateam1.service.TeamLeaderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeamLeaderServiceImpl implements TeamLeaderService {

    private final TeamLeaderRepository teamLeaderRepository;
    private final SignatoryMapper signatoryMapper;



    @Override
    public List<SignatoryDTO> findAll() {
        return teamLeaderRepository.findAll().stream().map(signatoryMapper::mapToDTO).toList();
    }

    @Override
    public SignatoryDTO findByName(String name) {
        TeamLeader teamLeader = teamLeaderRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("TeamLeader","name",name));
        return signatoryMapper.mapToDTO(teamLeader);
    }

    @Override
    public SignatoryDTO findById(Long id) {
        TeamLeader teamLeader = teamLeaderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TeamLeader","name",id));
        return signatoryMapper.mapToDTO(teamLeader);
    }

    @Override
    public SignatoryDTO findByActiveStatus(boolean active) {
        TeamLeader teamLeader = teamLeaderRepository.findByActiveStatus(active).orElseThrow(()-> new ResourceNotFoundException("No Team Leader is currently active!"));
        return signatoryMapper.mapToDTO(teamLeader);
    }

    @Override
    public SignatoryDTO add(SignatoryDTO signatoryDTO) throws Exception {
        Optional<TeamLeader> teamLeaderOptional = teamLeaderRepository.findByActiveStatus(true);
        TeamLeader teamLeader = signatoryMapper.mapToModel(signatoryDTO, TeamLeader.class);
        teamLeader.setActive(teamLeaderOptional.isEmpty());
        TeamLeader dbTeamLeader = teamLeaderRepository.save(teamLeader);
        signatoryDTO.setId(dbTeamLeader.getId());
        return signatoryDTO;
    }

    @Override
    @Transactional
    public SignatoryDTO update(SignatoryDTO signatoryDTO, Long id) {
        TeamLeader teamLeader = teamLeaderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TeamLeader","id",id));
        signatoryMapper.mapToModel(signatoryDTO,teamLeader);
        teamLeaderRepository.save(teamLeader);
        signatoryDTO.setId(id);
        return signatoryDTO;
    }

    @Override
    @Transactional
    public SignatoryDTO updateByActiveStatus(boolean active, long id) {
        teamLeaderRepository.updateByActiveStatus(active,id);
        TeamLeader teamLeader = teamLeaderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TeamLeader","id",id));
        return signatoryMapper.mapToDTO(teamLeader);
    }

    @Override
    public void delete(Long id) {
        teamLeaderRepository.deleteById(id);
    }
}
