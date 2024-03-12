package gov.coateam1.controller;

import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/employees")
public class PassengerController {

    private final EmployeeService employeeService;

    public PassengerController(@Qualifier("passengerServiceImpl")EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/passengers/all")
    public ResponseEntity<List<EmployeeDTO>> getAllPassengers(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/passengers")
    public ResponseEntity<EmployeeDTO> savePassenger(@RequestBody EmployeeDTO employeeDTO) throws Exception {
            return new ResponseEntity<>(employeeService.add(employeeDTO),HttpStatus.CREATED);
    }

    @PutMapping("/passengers/{id}")
    public ResponseEntity<EmployeeDTO> updatePassenger(@RequestBody EmployeeDTO employeeDTO,@PathVariable("id")Long id) throws Exception {

           return new ResponseEntity<>(employeeService.update(employeeDTO,id), HttpStatus.OK);

    }

    @DeleteMapping("/passengers/{id}")
    public ResponseEntity<APIResponse> deletePassenger(@PathVariable("id")Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @GetMapping("/passengers/{name}")
    public ResponseEntity<EmployeeDTO> getPassengerByName(@PathVariable("name")String name){
            return  new ResponseEntity<>(employeeService.findByName(name),HttpStatus.FOUND);
    }
}
