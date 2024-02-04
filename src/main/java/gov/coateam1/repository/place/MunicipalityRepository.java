package gov.coateam1.repository.place;

import gov.coateam1.model.place.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality,Long> {
}
