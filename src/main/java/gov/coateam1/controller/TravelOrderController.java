package gov.coateam1.controller;

import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.TravelOrderDTO;
import gov.coateam1.service.TravelOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TravelOrderController {

    private final TravelOrderService travelOrderService;



    @GetMapping("/travelorders/all")
    public ResponseEntity<List<TravelOrderDTO>> getAllTravelOrder(){
        List<TravelOrderDTO> travelOrders = travelOrderService.findAll();
        if(travelOrders.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(travelOrderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/travelorders/{dateIssued}")
    public ResponseEntity<List<TravelOrderDTO>> getAllTravelOrderByDateIssued(@PathVariable("dateIssued")String strDateIssued){
        return new ResponseEntity<>(travelOrderService.findTravelOrderByDateIssued(strDateIssued),HttpStatus.OK);
    }

    @GetMapping("/employees/{id}/travelorders")
    public ResponseEntity<List<TravelOrderDTO>> getTravelOrdersByEmployee(@PathVariable("id")Long id){
        return new ResponseEntity<>(travelOrderService.findTravelOrderByEmployeeId(id),HttpStatus.OK);
    }

    @PostMapping("/employees/{id}/travelorders")
    public ResponseEntity<TravelOrderDTO> saveTravelOrder( @PathVariable("id")Long id, @Valid @RequestBody TravelOrderDTO travelOrderDTO) throws Exception {
        return new ResponseEntity<>(travelOrderService.add(id,travelOrderDTO),HttpStatus.CREATED);
    }

    @PutMapping("/travelorders/{id}")
    public ResponseEntity<TravelOrderDTO> updateTravelOrder(@Valid @RequestBody TravelOrderDTO travelOrderDTO, @PathVariable("id")Long id) throws Exception {

            return new ResponseEntity<>(travelOrderService.update(travelOrderDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("/travelorders/{id}")
    public ResponseEntity<APIResponse> deleteTravelOrder(@PathVariable("id")Long id){
        travelOrderService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }







}
