package gov.coateam1.service;

import gov.coateam1.model.TeamLeader;

import java.util.List;

public interface TeamLeaderService {


    List<TeamLeader> findAll();

    TeamLeader findByActiveStatus(boolean active);

    TeamLeader add(TeamLeader teamLeader);

    TeamLeader update(TeamLeader teamLeader);

    TeamLeader updateByActiveStatus(boolean active, long id);

    void delete(Long id);
}
