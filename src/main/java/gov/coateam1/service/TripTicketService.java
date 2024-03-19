package gov.coateam1.service;

import gov.coateam1.payload.TripTicketDTO;

import java.util.List;

public interface TripTicketService {


    List<TripTicketDTO> findAll();


    TripTicketDTO findById(Long id);

    TripTicketDTO add(TripTicketDTO tripTicketDTO);

    TripTicketDTO update(TripTicketDTO tripTicketDTO, Long id);

    void delete(Long id);
}
