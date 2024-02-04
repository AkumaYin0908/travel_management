package gov.coateam1.repository;

import gov.coateam1.model.ReportTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportToRepository extends JpaRepository<ReportTo,Long> {
}
