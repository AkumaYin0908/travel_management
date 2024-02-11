package gov.coateam1.repository.place;

import gov.coateam1.model.place.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarangayRepository extends JpaRepository<Barangay,Long> {

    @Query("SELECT b FROM Barangay b WHERE b.name = :name")
    Optional<Barangay> findByName(@Param("name")String name);


}
