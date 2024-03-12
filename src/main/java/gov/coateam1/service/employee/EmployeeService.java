package gov.coateam1.service.employee;

import gov.coateam1.payload.employee.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO findById(Long id);
    EmployeeDTO findByName(String name);

    EmployeeDTO add(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();

    EmployeeDTO update(EmployeeDTO employeeDTO, Long id);

    void delete(Long id);

}
