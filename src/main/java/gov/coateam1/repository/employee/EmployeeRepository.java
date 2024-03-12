package gov.coateam1.repository.employee;

import gov.coateam1.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository<T extends Employee> extends JpaRepository<T,Long> {


    @Query("SELECT e FROM #{#entityName} AS e WHERE e.employeeType = :employeeType")
    T findByEmployeeType(@Param("employeeType") String employeeType);

    @Query("SELECT e FROM #{#entityName} AS e WHERE e.name =: name")
    Optional<T> findByName(@Param("name")String name);



}
