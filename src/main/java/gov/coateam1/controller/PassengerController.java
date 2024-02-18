package gov.coateam1.controller;

import gov.coateam1.payload.EmployeeDTO;
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

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllPassengers(){
        return new ResponseEntity<>(passengerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> savePassenger(@RequestBody EmployeeDTO employeeDTO) throws Exception {
            return new ResponseEntity<>(passengerService.add(employeeDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updatePassenger(@RequestBody EmployeeDTO employeeDTO,@PathVariable("id")Long id) throws Exception {

           return new ResponseEntity<>(passengerService.update(employeeDTO,id), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable("id")Long id){
        passengerService.delete(id);
        return new ResponseEntity<>("Delete successful!",HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<EmployeeDTO> getPassengerByName(@PathVariable("name")String name){
            return  new ResponseEntity<>(passengerService.findByName(name),HttpStatus.FOUND);
    }
}
