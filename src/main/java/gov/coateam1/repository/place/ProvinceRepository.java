package gov.coateam1.repository.place;

import gov.coateam1.model.place.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProvinceRepository extends JpaRepository<Province,String> {


}
