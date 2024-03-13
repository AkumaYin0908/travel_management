package gov.coateam1.service.impl.employee;

import gov.coateam1.model.Position;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.repository.PositionRepository;
import gov.coateam1.repository.employee.DriverRepository;
import gov.coateam1.repository.employee.EmployeeRepository;
import gov.coateam1.service.PositionService;
import gov.coateam1.service.employee.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements EmployeeService {

    private final EmployeeRepository<Driver> employeeRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;


    @Override
    public EmployeeDTO findById(Long id) {
        Driver driver = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Driver","id",id));
        return modelMapper.map(driver,EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findByName(String name) {
        log.info("DriverServiceImpl : findByName execution started");
        Driver driver = employeeRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Driver", "name", name));
        log.debug("DriverService:findByName received response from the database {}",driver);
        return modelMapper.map(driver, EmployeeDTO.class);
    }

    @Override
    @Transactional
    public EmployeeDTO add(EmployeeDTO employeeDTO)  {
        Driver driver = new Driver();
        driver.setName(employeeDTO.getName());

        Position position = positionRepository.findByName(employeeDTO.getPosition().getName())
                .orElse(new Position(employeeDTO.getPosition().getName()));
        position.addEmployee(driver);
        positionRepository.save(position);
        Driver dbDriver = employeeRepository.save(driver);
        employeeDTO.setId(dbDriver.getId());
        employeeDTO.getPosition().setId(dbDriver.getPosition().getId());
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream().map(emp->modelMapper.map(emp,EmployeeDTO.class)).toList();
    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id)  {

        Driver driver = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Driver", "id", id));
        driver.setName(employeeDTO.getName());
        driver.setPosition(modelMapper.map(employeeDTO.getPosition(),Position.class));
        employeeRepository.save(driver);
        employeeDTO.setId(id);
        return employeeDTO;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
