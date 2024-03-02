package gov.coateam1.service.impl.employee;

import gov.coateam1.model.Position;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.repository.employee.PassengerRepository;
import gov.coateam1.service.PositionService;
import gov.coateam1.service.employee.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements EmployeeService {

    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final PositionService positionService;


    @Override
    public EmployeeDTO findByName(String name) {
        Passenger passenger = passengerRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Passenger", "name", name));
        return modelMapper.map(passenger, EmployeeDTO.class);
    }

    @Override
    @Transactional
    public EmployeeDTO add(EmployeeDTO employeeDTO) {
        PositionDTO positionDTO = positionService.add(positionService.findByName(employeeDTO.getPosition().getName()));//if, position exist in db, merge, otherwise, persist
        Position position = modelMapper.map(employeeDTO.getPosition(), Position.class);
        Passenger passenger = modelMapper.map(employeeDTO, Passenger.class);
        passenger.setPosition(position);
        Passenger dbPassenger = passengerRepository.save(passenger);
        employeeDTO.setId(dbPassenger.getId());
        employeeDTO.setPosition(positionDTO);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {

        return passengerRepository.findAll().stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class)).toList();

    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id) {

        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passenger", "id", id));
        passenger.setName(employeeDTO.getName());
        passenger.setPosition(modelMapper.map(employeeDTO.getPosition(), Position.class));
        passengerRepository.save(passenger);
        employeeDTO.setId(id);
        return employeeDTO;


    }

    @Override
    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }

}
