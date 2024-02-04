package gov.coateam1.service.employee;

import gov.coateam1.exception.EmployeeNotFoundException;
import gov.coateam1.model.employee.Passenger;

import java.util.List;

public interface PassengerService {

    Passenger findByName(String name) throws EmployeeNotFoundException;


    Passenger addPassenger(Passenger passenger);


    List<Passenger> findAllPassenger();

    Passenger updatePassenger(Passenger passenger);

    void deletePassenger(Long id);

}
