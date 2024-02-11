package gov.coateam1.service.impl.employee;

import gov.coateam1.dto.EmployeeDTO;
import gov.coateam1.exception.EmployeeNotFoundException;
import gov.coateam1.mapper.EmployeeMapper;
import gov.coateam1.model.Position;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.repository.employee.PassengerRepository;
import gov.coateam1.service.PositionService;
import gov.coateam1.service.employee.PassengerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private  final EmployeeMapper employeeMapper;
    @Override
    public EmployeeDTO findByName(String name) {
        Passenger passenger = passengerRepository.findByName(name).orElseThrow(()->new EmployeeNotFoundException("Passenger not found!"));
        return employeeMapper.mapToDTO(passenger);
    }

    @Override
    @Transactional
    public EmployeeDTO add(EmployeeDTO employeeDTO) throws Exception {

        Passenger passenger = employeeMapper.maptoModel(employeeDTO,Passenger.class);
        Passenger dbPassenger = passengerRepository.save(passenger);
        employeeDTO.setId(dbPassenger.getId());

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {

        return  passengerRepository.findAll().stream().map(employeeMapper::mapToDTO).toList();

    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO) throws Exception {

      Passenger passenger=employeeMapper.maptoModel(employeeDTO,Passenger.class);

      passengerRepository.save(passenger);

      return  employeeDTO;


    }

    @Override
    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }

}
