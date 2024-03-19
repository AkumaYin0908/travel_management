package gov.coateam1.service.impl;

import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.*;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.Place;
import gov.coateam1.payload.TripTicketDTO;
import gov.coateam1.repository.TripTicketRepository;
import gov.coateam1.service.DistanceService;
import gov.coateam1.service.FuelBalanceService;
import gov.coateam1.service.TripTicketService;
import gov.coateam1.service.place.PlaceService;
import gov.coateam1.util.DateTimeConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
