package gov.coateam1.repository;

import gov.coateam1.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Optional<Vehicle> findByBrand(String brand);

    Optional<Vehicle> findByPlateNo(String plateNo);

    Optional<Vehicle> findByModel(String model);

    Optional<Vehicle> findByType(String type);


}
