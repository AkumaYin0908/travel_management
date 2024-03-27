package gov.coateam1.service.impl;

import gov.coateam1.mapper.TripTicketMapper;
import gov.coateam1.payload.trip_ticket.TripTicketDTO;
import gov.coateam1.repository.TripTicketRepository;
import gov.coateam1.service.TripTicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TripTicketServiceImpl implements TripTicketService {

    private final TripTicketRepository tripTicketRepository;

    private final TripTicketMapper tripTicketMapper;



    @Override
    public List<TripTicketDTO> findAll() {
        return tripTicketRepository.findAll().stream().map(tripTicketMapper::mapToDTO).toList();
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
