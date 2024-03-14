package gov.coateam1.controller;

import gov.coateam1.model.Vehicle;
import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> getAllVehicle(){
        return new ResponseEntity<>(vehicleService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        return new ResponseEntity<>(vehicleService.add(vehicleDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable("id")Long id){
        return new ResponseEntity<>(vehicleService.update(vehicleDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteVehicle(@PathVariable("id")Long id){
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @GetMapping("/{brand}")
    public ResponseEntity<VehicleDTO> getVehicleByBrand(@PathVariable("brand")String brand){
        return  new ResponseEntity<>(vehicleService.findByBrand(brand),HttpStatus.FOUND);
    }

    @GetMapping("/{model}")
    public ResponseEntity<VehicleDTO> getVehicleByModel(@PathVariable("brand")String model){
        return  new ResponseEntity<>(vehicleService.findByModel(model),HttpStatus.FOUND);
    }

    @GetMapping("/{plateNo}")
    public ResponseEntity<VehicleDTO> getVehicleByPlateNo(@PathVariable("plateNo")String plateNo){
        return  new ResponseEntity<>(vehicleService.findByPlateNo(plateNo),HttpStatus.FOUND);
    }

    @GetMapping("/{type}")
    public ResponseEntity<VehicleDTO> getVehicleByType(@PathVariable("type")String type){
        return  new ResponseEntity<>(vehicleService.findByType(type),HttpStatus.FOUND);
    }



}
