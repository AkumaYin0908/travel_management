package gov.coateam1.service;

import gov.coateam1.payload.trip_ticket.TripTicketDTO;

import java.util.List;

public interface TripTicketService {


    List<TripTicketDTO> findAll();


    TripTicketDTO findById(Long id);

    TripTicketDTO add(Long driverId, TripTicketDTO tripTicketDTO) throws Exception;

    TripTicketDTO update(TripTicketDTO tripTicketDTO, Long id) throws Exception;

    void delete(Long id);
}
