package gov.coateam1.controller;


import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.service.employee.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/employees")
public class DriverController {


    private final EmployeeService employeeService;

    public DriverController(@Qualifier("driverServiceImpl")EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/drivers/all")
    public ResponseEntity<List<EmployeeDTO>> getAllDriver(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/drivers")
    public ResponseEntity<EmployeeDTO> saveDriver(@Valid @RequestBody  EmployeeDTO employeeDTO)  {
            return new ResponseEntity<>(employeeService.add(employeeDTO),HttpStatus.CREATED);
    }

    @PutMapping("/drivers/{id}")
    public ResponseEntity<EmployeeDTO> updateDriver(@Valid @RequestBody EmployeeDTO employeeDTO,@PathVariable("id")Long id) throws Exception {
            return new ResponseEntity<>(employeeService.update(employeeDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<APIResponse> deleteDriver(@PathVariable("id")Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @GetMapping("/drivers/{name}")
    public ResponseEntity<EmployeeDTO> getDriverByName(@PathVariable("name")String name){
            return  new ResponseEntity<>(employeeService.findByName(name),HttpStatus.FOUND);
    }





}
