package gov.coateam1.controller;

import gov.coateam1.dto.EmployeeDTO;
import gov.coateam1.model.Position;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.service.PositionService;
import gov.coateam1.service.employee.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;
    private final PositionService positionService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllPassengers(){
        return new ResponseEntity<>(passengerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePassenger(@RequestBody EmployeeDTO employeeDTO){
        try{
            return new ResponseEntity<>(passengerService.add(employeeDTO),HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePassenger(@RequestBody EmployeeDTO employeeDTO){
       try{
           return new ResponseEntity<>(passengerService.update(employeeDTO), HttpStatus.OK);
       }catch (Exception ex){
           return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable("id")Long id){
        passengerService.delete(id);
        return new ResponseEntity<>("Delete successful!",HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<?> getPassengerByName(@PathVariable("name")String name){
        try{
            return  new ResponseEntity<>(passengerService.findByName(name),HttpStatus.FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
