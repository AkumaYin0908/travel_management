package gov.coateam1.repository.place;

import gov.coateam1.model.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {


}
