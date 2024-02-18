package gov.coateam1.controller;

import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.EmployeeDTO;
import gov.coateam1.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passengers")
public class PassengerController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllPassengers(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> savePassenger(@RequestBody EmployeeDTO employeeDTO) throws Exception {
            return new ResponseEntity<>(employeeService.add(employeeDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updatePassenger(@RequestBody EmployeeDTO employeeDTO,@PathVariable("id")Long id) throws Exception {

           return new ResponseEntity<>(employeeService.update(employeeDTO,id), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deletePassenger(@PathVariable("id")Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<EmployeeDTO> getPassengerByName(@PathVariable("name")String name){
            return  new ResponseEntity<>(employeeService.findByName(name),HttpStatus.FOUND);
    }
}
