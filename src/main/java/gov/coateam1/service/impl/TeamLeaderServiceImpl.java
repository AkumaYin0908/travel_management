package gov.coateam1.service.impl;

import gov.coateam1.exception.NoActiveTeamLeaderException;
import gov.coateam1.model.TeamLeader;
import gov.coateam1.repository.TeamLeaderRepository;
import gov.coateam1.service.TeamLeaderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeamLeaderServiceImpl implements TeamLeaderService {

    private final TeamLeaderRepository teamLeaderRepository;

    @Override
    public List<TeamLeader> findAll() {
        return teamLeaderRepository.findAll();
    }

    @Override
    public TeamLeader findByActiveStatus(boolean active) {
        return teamLeaderRepository.findByActiveStatus(active).orElseThrow(()-> new NoActiveTeamLeaderException("No Team Leader is currently active!"));
    }

    @Override
    public TeamLeader add(TeamLeader teamLeader) {
        return teamLeaderRepository.save(teamLeader);
    }

    @Override
    public TeamLeader update(TeamLeader teamLeader) {
        return teamLeaderRepository.save(teamLeader);
    }

    @Override
    public TeamLeader updateByActiveStatus(boolean active, long id) {
        return teamLeaderRepository.updateByActiveStatus(active,id);
    }

    @Override
    public void delete(Long id) {
        teamLeaderRepository.deleteById(id);
    }
}
