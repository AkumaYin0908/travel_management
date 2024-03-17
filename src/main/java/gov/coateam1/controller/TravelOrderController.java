package gov.coateam1.controller;

import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.TravelOrderDTO;
import gov.coateam1.service.TravelOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TravelOrderController {

    private final TravelOrderService travelOrderService;



    @GetMapping("/travelorders/all")
    public ResponseEntity<List<TravelOrderDTO>> getAllTravelOrders(){
        return new ResponseEntity<>(travelOrderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/employees/{id}/travelorders")
    public ResponseEntity<TravelOrderDTO> saveTravelOrder(@PathVariable("id")Long id, @RequestBody TravelOrderDTO travelOrderDTO) {
        try{
            return new ResponseEntity<>(travelOrderService.add(id,travelOrderDTO),HttpStatus.CREATED);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/employees/{employeeId}/travelorders/{id}")
    public ResponseEntity<TravelOrderDTO> updateTravelOrder(@RequestBody TravelOrderDTO travelOrderDTO, @PathVariable("id")Long id) throws Exception {

        try{
            return new ResponseEntity<>(travelOrderService.update(travelOrderDTO,id),HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/travelorders/{id}")
    public ResponseEntity<APIResponse> deleteTravelOrder(@PathVariable("id")Long id){
        travelOrderService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }







}
