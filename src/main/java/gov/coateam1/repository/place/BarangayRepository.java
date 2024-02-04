package gov.coateam1.repository.place;

import gov.coateam1.model.place.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangayRepository extends JpaRepository<Barangay,Long> {
}
