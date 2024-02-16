package gov.coateam1.service.impl;

import gov.coateam1.exception.NoActiveTeamLeaderException;
import gov.coateam1.exception.TeamLeaderNotFoundException;
import gov.coateam1.model.TeamLeader;
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

    @Override
    public List<TeamLeader> findAll() {
        return teamLeaderRepository.findAll();
    }

    @Override
    public TeamLeader findByName(String name) {
        return teamLeaderRepository.findByName(name).orElseThrow(()->new TeamLeaderNotFoundException("Team Leader not found!"));
    }

    @Override
    public TeamLeader findById(Long id) {
        return teamLeaderRepository.findById(id).orElseThrow(()->new TeamLeaderNotFoundException("Team Leader not found!"));
    }

    @Override
    public TeamLeader findByActiveStatus(boolean active) {
        return teamLeaderRepository.findByActiveStatus(active).orElseThrow(()-> new NoActiveTeamLeaderException("No Team Leader is currently active!"));
    }

    @Override
    public TeamLeader add(TeamLeader teamLeader) {
        Optional<TeamLeader> teamLeaderOptional = teamLeaderRepository.findByActiveStatus(true);
        teamLeader.setActive(teamLeaderOptional.isEmpty());
        return teamLeaderRepository.save(teamLeader);
    }

    @Override
    @Transactional
    public TeamLeader update(TeamLeader teamLeader) {
        return teamLeaderRepository.save(teamLeader);
    }

    @Override
    @Transactional
    public void updateByActiveStatus(boolean active, long id) {
        teamLeaderRepository.updateByActiveStatus(active,id);
    }

    @Override
    public void delete(Long id) {
        teamLeaderRepository.deleteById(id);
    }
}
