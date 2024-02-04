package gov.coateam1.repository;

import gov.coateam1.model.FuelBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelBalanceRepository extends JpaRepository<FuelBalance,Long> {
}
