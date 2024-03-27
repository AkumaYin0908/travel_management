package gov.coateam1.controller;


import gov.coateam1.service.TripTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip-ticket")
public class TripTicketController {

    private  final TripTicketService tripTicketService;


}
