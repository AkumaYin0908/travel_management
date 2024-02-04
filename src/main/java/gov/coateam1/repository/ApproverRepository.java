package gov.coateam1.repository;

import gov.coateam1.model.Approver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApproverRepository extends JpaRepository<Approver,Long> {
}
