package gov.coateam1.mapper;

import gov.coateam1.model.employee.Driver;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.payload.PositionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {




    private final PositionMapper positionMapper;

    public EmployeeDTO mapToDTO(Employee employee){
        PositionDTO positionDTO = positionMapper.mapToDTO(employee.getPosition());
        return new EmployeeDTO(employee.getId(), employee.getName(),employee.getPosition().getName());
    }

    public  <T extends Employee> T maptoModel(EmployeeDTO employeeDTO,Class<T> clazz) throws Exception{

        T employee=  clazz.getDeclaredConstructor().newInstance();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        return  employee;
    }

    public <T extends Employee> void maptoModel(EmployeeDTO employeeDTO, T employee) throws Exception{
        employee.setName(employeeDTO.getName());
    }








}
