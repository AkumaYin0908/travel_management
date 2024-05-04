package gov.coateam1.service.impl.employee;

import gov.coateam1.model.Position;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.repository.PositionRepository;
import gov.coateam1.repository.employee.EmployeeRepository;
import gov.coateam1.service.employee.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@EnableCaching
public class DriverServiceImpl implements EmployeeService {

    private final EmployeeRepository<Driver> employeeRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;


    @Cacheable("driver")
    @Override
    public EmployeeDTO findById(Long id) {
        Driver driver = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Driver","id",id));
        return modelMapper.map(driver,EmployeeDTO.class);
    }

    @Cacheable("driver")
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
        Position position = positionRepository.findById(employeeDTO.getPosition().getId())
                .orElse(new Position(employeeDTO.getPosition().getName()));
        position.addEmployee(driver);
        positionRepository.save(position);
        Driver dbDriver = employeeRepository.save(driver);

        return modelMapper.map(dbDriver,EmployeeDTO.class);
    }

    @Cacheable("drivers")
    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream().map(emp->modelMapper.map(emp,EmployeeDTO.class)).collect(Collectors.toList());
    }

    @CachePut(value = "driver", key = "#id")
    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id)  {

        Driver driver = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Driver", "id", id));
        driver.setName(employeeDTO.getName());

        String positionName = employeeDTO.getPosition().getName();
        Position position = positionRepository.findByName(positionName).orElse(new Position(positionName));
        positionRepository.save(position);

        driver.setPosition(position);
        Driver dbDriver = employeeRepository.save(driver);
        return modelMapper.map(dbDriver,EmployeeDTO.class);
    }

    @CacheEvict(value="driver", key="#id")
    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
