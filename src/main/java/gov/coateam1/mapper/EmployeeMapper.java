package gov.coateam1.mapper;

import gov.coateam1.payload.EmployeeDTO;
import gov.coateam1.model.Position;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final PositionService positionService;

    public EmployeeDTO mapToDTO(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getName(),employee.getPosition().getName());
    }

    public <T extends Employee> T maptoModel(EmployeeDTO employeeDTO,Class<T> instance) throws Exception{
        Position position = positionService.findByName(employeeDTO.getPosition());
        T employee=instance.getDeclaredConstructor().newInstance();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setPosition(position);
        return  employee;
    }

    public <T extends Employee> void maptoModel(EmployeeDTO employeeDTO, T employee) throws Exception{
        Position position = positionService.findByName(employeeDTO.getPosition());
        employee.setName(employeeDTO.getName());
        employee.setPosition(position);
    }








}
