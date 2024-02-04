package gov.coateam1.repository;

import gov.coateam1.model.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamLeaderRepository extends JpaRepository<TeamLeader,Long> {
}
