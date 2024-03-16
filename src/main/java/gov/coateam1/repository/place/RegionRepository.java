package gov.coateam1.repository.place;

import gov.coateam1.model.place.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {
}