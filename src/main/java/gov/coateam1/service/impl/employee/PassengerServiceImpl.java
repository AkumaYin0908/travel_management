package gov.coateam1.service.impl.employee;

import gov.coateam1.exception.EmployeeNotFoundException;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.repository.employee.PassengerRepository;
import gov.coateam1.service.employee.PassengerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    @Override
    public Passenger findByName(String name) {
        return passengerRepository.findByName(name).orElseThrow(()->new EmployeeNotFoundException("Passenger not found!"));
    }

    @Override
    @Transactional
    public Passenger add(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    @Transactional
    public Passenger update(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }
}
