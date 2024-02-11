package gov.coateam1.service.impl.employee;

import gov.coateam1.exception.EmployeeNotFoundException;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.repository.employee.DriverRepository;
import gov.coateam1.service.employee.DriverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public Driver findByName(String name) {
        return driverRepository.findByName(name).orElseThrow(()->new EmployeeNotFoundException("Driver not found!"));
    }

    @Override
    @Transactional
    public Driver add(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    @Transactional
    public Driver update(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }
}
