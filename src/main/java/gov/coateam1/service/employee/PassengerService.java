package gov.coateam1.service.employee;

import gov.coateam1.exception.EmployeeNotFoundException;
import gov.coateam1.model.employee.Passenger;

import java.util.List;

public interface PassengerService {

    Passenger findByName(String name);

    Passenger add(Passenger passenger);

    List<Passenger> findAll();

    Passenger update(Passenger passenger);

    void delete(Long id);

}
