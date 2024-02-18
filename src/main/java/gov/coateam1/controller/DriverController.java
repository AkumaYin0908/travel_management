package gov.coateam1.controller;


import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.EmployeeDTO;
import gov.coateam1.service.employee.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllDrivers(){
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> saveDriver(@RequestBody EmployeeDTO employeeDTO) throws Exception {
            return new ResponseEntity<>(driverService.add(employeeDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateDriver(@RequestBody EmployeeDTO employeeDTO,@PathVariable("id")Long id) throws Exception {
            return new ResponseEntity<>(driverService.update(employeeDTO,id),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteDriver(@PathVariable("id")Long id){
        driverService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<EmployeeDTO> getDriverByName(@PathVariable("name")String name){
            return  new ResponseEntity<>(driverService.findByName(name),HttpStatus.FOUND);
    }



}
