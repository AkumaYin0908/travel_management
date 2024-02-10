package gov.coateam1.repository;

import gov.coateam1.model.ReportTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReportToRepository extends JpaRepository<ReportTo,Long> {

    @Query("SELECT reportTo ReportTo reportTo WHERE reportTo.name = :name")
    Optional<ReportTo> findByName(@Param("name") String name);



}
