package gov.coateam1.service.employee;

import gov.coateam1.exception.EmployeeNotFoundException;
import gov.coateam1.model.employee.Driver;

import java.util.List;

public interface DriverService {

    Driver findByName(String name) throws EmployeeNotFoundException;

    Driver add(Driver driver);

    List<Driver> findAll();

    Driver update(Driver driver);

    void delete(Long id);

}
