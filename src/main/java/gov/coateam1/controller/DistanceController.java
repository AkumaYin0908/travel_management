package gov.coateam1.controller;


import gov.coateam1.model.Distance;
import gov.coateam1.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/distance")
public class DistanceController {

    private final DistanceService distanceService;

    @GetMapping
    public ResponseEntity<Distance> getDistance(){
        return new ResponseEntity<>(distanceService.findDistance(), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Distance> addDistance(@RequestBody Distance distance){
        return new ResponseEntity<>(distanceService.add(distance),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Distance> updateDistance(@RequestParam("startDistance")Long startDistance){
        return new ResponseEntity<>(distanceService.update(startDistance),HttpStatus.OK);
    }


}
