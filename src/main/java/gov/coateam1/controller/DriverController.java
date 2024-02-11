package gov.coateam1.controller;


import gov.coateam1.model.employee.Driver;
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
    public ResponseEntity<List<Driver>> getAllDrivers(){
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Driver> saveDriver(@RequestBody Driver driver){
        return new ResponseEntity<>(driverService.add(driver),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver){
        return new ResponseEntity<>(driverService.update(driver), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable("id")Long id){
        driverService.delete(id);
        return new ResponseEntity<>("Delete successful!",HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<?> getDriverByName(@PathVariable("name")String name){
        try{
            return  new ResponseEntity<>(driverService.findByName(name),HttpStatus.FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }



}
