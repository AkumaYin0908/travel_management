package gov.coateam1.repository;

import gov.coateam1.constants.VehicleQueryConstant;
import gov.coateam1.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {


    @Query(value = VehicleQueryConstant.FIND_BY_BRAND,nativeQuery = true)
    List<Vehicle> findByBrand(String brand);

    Optional<Vehicle> findByPlateNo(String plateNo);

    @Query(value = VehicleQueryConstant.FIND_BY_MODEL,nativeQuery = true)
    List<Vehicle>  findByModel(String model);

    @Query(value = VehicleQueryConstant.FIND_BY_TYPE,nativeQuery = true)
    List<Vehicle>  findByType(String type);


}
