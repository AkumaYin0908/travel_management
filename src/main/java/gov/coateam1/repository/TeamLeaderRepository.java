package gov.coateam1.repository;

import gov.coateam1.model.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeamLeaderRepository extends JpaRepository<TeamLeader,Long> {

    @Query("SELECT tl FROM TeamLeader tl WHERE tl.active = :active")
    Optional<TeamLeader> findByActiveStatus(@Param("active") boolean active);


    @Query("UPDATE TeamLeader tl SET tl.active = :active WHERE tl.id = :id")
    TeamLeader updateByActiveStatus(@Param("active") boolean active, @Param("id") Long id);
}
