package gov.coateam1.controller;

import gov.coateam1.model.FuelBalance;
import gov.coateam1.service.FuelBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fuelbalance")
public class FuelBalanceController {

    private final FuelBalanceService fuelBalanceService;

    @GetMapping
    public ResponseEntity<FuelBalance> getDistance(){
        return new ResponseEntity<>(fuelBalanceService.findFuelBalance(), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<FuelBalance> addFuelBalance(@RequestBody FuelBalance fuelBalance){
        return new ResponseEntity<>(fuelBalanceService.add(fuelBalance),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<FuelBalance> updateFuelBalance(@RequestParam("fuelBalance")BigDecimal fuelBalance){
        return new ResponseEntity<>(fuelBalanceService.update(fuelBalance), HttpStatus.OK);
    }
}
