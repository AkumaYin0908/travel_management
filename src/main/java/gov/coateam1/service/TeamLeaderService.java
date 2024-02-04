package gov.coateam1.service;

import gov.coateam1.model.TeamLeader;

public interface TeamLeaderService {


    TeamLeader findTeamLeaderByActiveStatus(boolean active);

    TeamLeader addTeamLeader(TeamLeader teamLeader);

    TeamLeader updateTeamLeader(TeamLeader teamLeader);

    TeamLeader updateTeamLeaderByActiveStatus(boolean active, long id);

    void deleteTeamLeader(Long id);
}
