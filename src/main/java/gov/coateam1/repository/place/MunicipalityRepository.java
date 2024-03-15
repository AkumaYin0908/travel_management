package gov.coateam1.repository.place;

import gov.coateam1.model.place.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality,Long> {

}
