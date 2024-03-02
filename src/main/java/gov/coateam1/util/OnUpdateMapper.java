package gov.coateam1.util;

import gov.coateam1.model.Position;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.payload.employee.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class OnUpdateMapper {

   private static final ModelMapper modelMapper = new ModelMapper();

    public static <T extends Employee> void maptoModel(EmployeeDTO employeeDTO, T employee) throws Exception{
        employee.setName(employeeDTO.getName());
        employee.setPosition(modelMapper.map(employeeDTO.getPosition(), Position.class));
    }
}
