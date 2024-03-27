package gov.coateam1.controller;


import gov.coateam1.payload.trip_ticket.TripTicketDTO;
import gov.coateam1.service.TripTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip-ticket")
public class TripTicketController {

    private  final TripTicketService tripTicketService;


    @GetMapping("/all")
    public ResponseEntity<List<TripTicketDTO>> getAllTripTicket(){
        return new ResponseEntity<>(tripTicketService.findAll(), HttpStatus.OK);
    }


}
