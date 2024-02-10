package gov.coateam1.repository;

import gov.coateam1.model.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurposeRepository extends JpaRepository<Purpose,Long> {


    Optional<Purpose> findByPurpose(String purpose);


}
