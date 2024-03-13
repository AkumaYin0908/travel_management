package gov.coateam1.service.impl.employee;

import gov.coateam1.model.Position;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.repository.PositionRepository;
import gov.coateam1.repository.employee.EmployeeRepository;
import gov.coateam1.service.PositionService;
import gov.coateam1.service.employee.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PassengerServiceImpl implements EmployeeService {
    private final EmployeeRepository<Passenger> employeeRepository;
    private final ModelMapper modelMapper;
    private final PositionRepository positionRepository;


    @Override
    public EmployeeDTO findById(Long id) {
        Passenger passenger = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Passenger","id",id));
        return modelMapper.map(passenger,EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findByName(String name) {
        Passenger passenger = employeeRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Passenger", "name", name));
        return modelMapper.map(passenger, EmployeeDTO.class);
    }

    @Override
    @Transactional
    public EmployeeDTO add(EmployeeDTO employeeDTO) {
        Passenger passenger = new Passenger();
        passenger.setName(employeeDTO.getName());


        Position position = positionRepository.findByName(employeeDTO.getPosition().getName())
                .orElse(new Position(employeeDTO.getPosition().getName()));
        position.addEmployee(passenger);

        Position dbPosition = positionRepository.save(position);
        Passenger dbPassenger = employeeRepository.save(passenger);
        employeeDTO.setId(dbPassenger.getId());
        employeeDTO.getPosition().setId(dbPassenger.getPosition().getId());
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {

        return employeeRepository.findAll().stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class)).toList();

    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id) {

        Passenger passenger = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passenger", "id", id));
        passenger.setName(employeeDTO.getName());
        passenger.setPosition(modelMapper.map(employeeDTO.getPosition(), Position.class));
        employeeRepository.save(passenger);
        employeeDTO.setId(id);
        return employeeDTO;


    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

}
