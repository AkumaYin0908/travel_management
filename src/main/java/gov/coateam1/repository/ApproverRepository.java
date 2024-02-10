package gov.coateam1.repository;

import gov.coateam1.model.Approver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ApproverRepository extends JpaRepository<Approver,Long> {

    @Query("SELECT a FROM Approver a WHERE a.active = :active")
    Optional<Approver> findByActiveStatus(@Param("active") boolean active);


    @Query("UPDATE Approver a SET  a.active = :active WHERE a.id = :id")
    @Modifying
    Approver updateActiveStatus(@Param("active") boolean active, @Param("id")Long id);
}
