package gov.coateam1.repository;

import gov.coateam1.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DistanceRepository extends JpaRepository<Distance,Long> {

    @Query("SELECT distance from Distance distance")
    Optional<Distance> findDistance();
}
