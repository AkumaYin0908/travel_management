package gov.coateam1.service.impl.employee;

import gov.coateam1.mapper.PositionMapper;
import gov.coateam1.model.Position;
import gov.coateam1.payload.PositionDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.EmployeeMapper;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.repository.employee.PassengerRepository;
import gov.coateam1.service.PositionService;
import gov.coateam1.service.employee.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements EmployeeService {

    private final PassengerRepository passengerRepository;
    private  final EmployeeMapper employeeMapper;
    private final PositionService positionService;
    private final PositionMapper positionMapper;
    @Override
    public EmployeeDTO findByName(String name) {
        Passenger passenger = passengerRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Passenger","name",name));
        return employeeMapper.mapToDTO(passenger);
    }

    @Override
    @Transactional
    public EmployeeDTO add(EmployeeDTO employeeDTO) throws Exception {
        PositionDTO positionDTO =  positionService.add(positionService.findByName(employeeDTO.getPosition().getName()));//if, position exist in db, merge, otherwise, persist
        Position position = positionMapper.mapToModel(positionDTO);
        Passenger passenger = employeeMapper.maptoModel(employeeDTO, Passenger.class);
        passenger.setPosition(position);
        Passenger dbPassenger = passengerRepository.save(passenger);
        employeeDTO.setId(dbPassenger.getId());
        employeeDTO.setPosition(positionDTO);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {

        return  passengerRepository.findAll().stream().map(employeeMapper::mapToDTO).toList();

    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO, Long id) throws Exception {

      Passenger passenger=passengerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Passenger","id",id));
      employeeMapper.maptoModel(employeeDTO,passenger);
      passengerRepository.save(passenger);
      employeeDTO.setId(id);
      return  employeeDTO;


    }

    @Override
    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }

}
