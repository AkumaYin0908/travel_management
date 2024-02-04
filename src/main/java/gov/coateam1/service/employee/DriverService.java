package gov.coateam1.service.employee;

import gov.coateam1.exception.EmployeeNotFoundException;
import gov.coateam1.model.employee.Driver;

import java.util.List;

public interface DriverService {

    Driver findByName(String name) throws EmployeeNotFoundException;

    Driver addDriver(Driver driver);

    List<Driver> findAllDrivers();

    Driver updateDriver(Driver driver);

    void deleteDriver(Long id);

}
