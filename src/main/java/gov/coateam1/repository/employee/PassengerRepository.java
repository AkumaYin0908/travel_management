package gov.coateam1.repository.employee;


import gov.coateam1.model.employee.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {

    @Query("from Passenger where name= :name")
    Optional<Passenger> findPassengerByName(@Param("name")String name);
}
