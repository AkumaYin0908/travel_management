package gov.coateam1.repository.place;

import gov.coateam1.model.place.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {
}
