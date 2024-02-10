package gov.coateam1.repository;

import gov.coateam1.model.FuelBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface FuelBalanceRepository extends JpaRepository<FuelBalance,Long> {

    @Query("SELECT fuelBalance FROM FuelBalance fuelBalance")
    Optional<FuelBalance> findFuelBalance();
}
