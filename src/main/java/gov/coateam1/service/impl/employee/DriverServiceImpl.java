package gov.coateam1.service.impl.employee;

import gov.coateam1.model.Position;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.repository.employee.DriverRepository;
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

    private final DriverRepository driverRepository;
    private final PositionService positionService;
    private final ModelMapper modelMapper;




    @Override
    public EmployeeDTO findByName(String name) {
        log.info("DriverServiceImpl : findByName execution started");
        Driver driver = driverRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Driver", "name", name));
        log.debug("DriverService:findByName received response from the database {}",driver);
        return modelMapper.map(driver, EmployeeDTO.class);
    }

    @Override
    @Transactional
    public EmployeeDTO add(EmployeeDTO employeeDTO)  {

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
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id)  {

        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Driver", "id", id));
        driver.setName(employeeDTO.getName());
        driver.setPosition(modelMapper.map(employeeDTO.getPosition(),Position.class));
        driverRepository.save(driver);
        employeeDTO.setId(id);
        return employeeDTO;
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }
}
