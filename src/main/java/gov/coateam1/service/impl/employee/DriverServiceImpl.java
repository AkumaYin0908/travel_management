package gov.coateam1.service.impl.employee;

import gov.coateam1.mapper.PositionMapper;
import gov.coateam1.model.Position;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.EmployeeMapper;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.repository.employee.DriverRepository;
import gov.coateam1.service.PositionService;
import gov.coateam1.service.employee.EmployeeService;
import gov.coateam1.util.OnUpdateMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements EmployeeService {

    private final DriverRepository driverRepository;
    private final PositionService positionService;
    private final ModelMapper modelMapper;




    @Override
    public EmployeeDTO findByName(String name) {
        Driver driver = driverRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Driver", "name", name));
        return modelMapper.map(driver, EmployeeDTO.class);
    }

    @Override
    @Transactional
    public EmployeeDTO add(EmployeeDTO employeeDTO) throws Exception {

        PositionDTO positionDTO = positionService.add(positionService.findByName(employeeDTO.getPosition().getName()));//if, position exist in db, merge, otherwise, persist
        Position position = modelMapper.map(positionDTO, Position.class);
        Driver driver = modelMapper.map(employeeDTO, Driver.class);
        driver.setPosition(position);
        Driver dbDriver = driverRepository.save(driver);
        employeeDTO.setId(dbDriver.getId());
        employeeDTO.setPosition(positionDTO);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {

        return driverRepository.findAll().stream().map(emp->modelMapper.map(emp,EmployeeDTO.class)).toList();
    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id) throws Exception {

        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Driver", "id", id));
        OnUpdateMapper.maptoModel(employeeDTO,driver);
        driverRepository.save(driver);
        employeeDTO.setId(id);
        return employeeDTO;
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }
}
