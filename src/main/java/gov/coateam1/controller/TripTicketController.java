package gov.coateam1.controller;


import gov.coateam1.payload.APIResponse;
import gov.coateam1.payload.trip_ticket.TripTicketDTO;
import gov.coateam1.service.TripTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripTicketController {

    private  final TripTicketService tripTicketService;

    @GetMapping("/triptickets/all")
    public ResponseEntity<List<TripTicketDTO>> getAllTripTicket(){
        List<TripTicketDTO> tripTickets = tripTicketService.findAll();
        if(tripTickets.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tripTicketService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/triptickets/{id}")
    public ResponseEntity<TripTicketDTO> getTripTicketById(@PathVariable("id")Long id){
        return new ResponseEntity<>(tripTicketService.findById(id),HttpStatus.FOUND);
    }


    @PostMapping("/employees/{id}/triptickets")
    public ResponseEntity<TripTicketDTO> saveTripTicket(@PathVariable("id")Long id, @RequestBody TripTicketDTO tripTicketDTO) throws Exception{
        return new ResponseEntity<>(tripTicketService.add(id, tripTicketDTO), HttpStatus.CREATED);
    }

    @PutMapping("/triptickets/{id}")
    public ResponseEntity<TripTicketDTO> updateTripTicket(@RequestBody TripTicketDTO tripTicketDTO, @PathVariable("id") Long id) throws Exception{
        return new ResponseEntity<>(tripTicketService.update(tripTicketDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("/tripticket/{id}")
    public ResponseEntity<APIResponse> deleteTripTicket(@PathVariable("id")Long id){
        tripTicketService.delete(id);
        return new ResponseEntity<>(new APIResponse("Delete successful!",true,HttpStatus.OK.value()),HttpStatus.OK);
    }



}
