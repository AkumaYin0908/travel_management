package gov.coateam1.repository.employee;

import gov.coateam1.model.employee.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {


    @Query("from Driver where name= :name")
    Optional<Driver> findByName(@Param("name")String name);


}
