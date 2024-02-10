package gov.coateam1.repository;

import gov.coateam1.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {


    @Query("SELECT p FROM Position p WHERE p.name = :name")
    Optional<Position> findByName(@Param("name") String name);
}
