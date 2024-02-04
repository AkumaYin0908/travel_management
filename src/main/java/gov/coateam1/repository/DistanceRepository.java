package gov.coateam1.repository;

import gov.coateam1.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DistanceRepository extends JpaRepository<Distance,Long> {
}
