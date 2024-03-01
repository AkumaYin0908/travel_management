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

@RequestMapping("/drivers")
public class DriverController {


    private final EmployeeService employeeService;

    public DriverController(@Qualifier("driverServiceImpl")EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllDrivers(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveDriver(@RequestBody EmployeeDTO employeeDTO) throws Exception {
            return new ResponseEntity<>(employeeService.add(employeeDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateDriver(@RequestBody EmployeeDTO employeeDTO,@PathVariable("id")Long id) throws Exception {
            return new ResponseEntity<>(employeeService.update(employeeDTO,id),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteDriver(@PathVariable("id")Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<EmployeeDTO> getDriverByName(@PathVariable("name")String name){
            return  new ResponseEntity<>(employeeService.findByName(name),HttpStatus.FOUND);
    }





}
