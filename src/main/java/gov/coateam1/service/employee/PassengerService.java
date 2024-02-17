package gov.coateam1.service.employee;

import gov.coateam1.dto.EmployeeDTO;

import java.util.List;

public interface PassengerService {

    EmployeeDTO findByName(String name);

    EmployeeDTO add(EmployeeDTO employeeDTO) throws Exception;

    List<EmployeeDTO> findAll();

    EmployeeDTO update(EmployeeDTO employeeDTO) throws Exception;

    void delete(Long id);

}
