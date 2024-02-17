package gov.coateam1.service.employee;

import gov.coateam1.dto.EmployeeDTO;

import java.util.List;

public interface DriverService {

    EmployeeDTO findByName(String name);

    EmployeeDTO add(EmployeeDTO employeeDTO) throws Exception;

    List<EmployeeDTO> findAll();

    EmployeeDTO update(EmployeeDTO employeeDTO, Long id) throws Exception;

    void delete(Long id);

}
