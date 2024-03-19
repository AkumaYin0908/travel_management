package gov.coateam1.service.impl;

import gov.coateam1.payload.trip_ticket.TripTicketDTO;
import gov.coateam1.repository.TripTicketRepository;
import gov.coateam1.service.TripTicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TripTicketServiceImpl implements TripTicketService {

    private final TripTicketRepository tripTicketRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<TripTicketDTO> findAll() {
        return null;
    }

    @Override
    public TripTicketDTO findById(Long id) {
        return null;
    }

    @Override
    public TripTicketDTO add(TripTicketDTO tripTicketDTO) {
        return null;
    }

    @Override
    public TripTicketDTO update(TripTicketDTO tripTicketDTO, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        tripTicketRepository.deleteById(id);
    }
}
