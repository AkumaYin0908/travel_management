package gov.coateam1.controller;

import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.TravelOrderDTO;
import gov.coateam1.service.TravelOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travelorders")
@RequiredArgsConstructor
public class TravelOrderController {

    private final TravelOrderService travelOrderService;



    @GetMapping("/all")
    public ResponseEntity<List<TravelOrderDTO>> getAllTravelOrders(){
        return new ResponseEntity<>(travelOrderService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TravelOrderDTO> saveTravelOrder(@RequestBody TravelOrderDTO travelOrderDTO){
        return new ResponseEntity<>(travelOrderService.add(travelOrderDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelOrderDTO> updateTravelOrder(@RequestBody TravelOrderDTO travelOrderDTO, @PathVariable("id")Long id){
        return new ResponseEntity<>(travelOrderService.update(travelOrderDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteTravelOrder(@PathVariable("id")Long id){
        travelOrderService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }



}
