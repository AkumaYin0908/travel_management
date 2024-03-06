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
    private final FuelBalanceService fuelBalanceService;
    private final DistanceService distanceService;
    private final PlaceService placeService;

    private final ModelMapper modelMapper;

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");


    private TripTicketDTO convertToDTO(TripTicket tripTicket){
        Converter<LocalDate, String> toDateString =
                context -> context.getSource() == null ? null : dateFormatter.format(context.getSource());

        Converter<LocalTime,String> toTimeString =
                context -> context.getSource() == null ? null : timeFormatter.format(context.getSource());

        modelMapper.typeMap(TripTicket.class, TripTicketDTO.class)
                .addMappings(mapping -> mapping.using(toDateString).map(TripTicket::getDateDeparture,TripTicketDTO::setDateDeparture))
                .addMappings(mapping -> mapping.using(toDateString).map(TripTicket::getDateReturn,TripTicketDTO::setDateReturn))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTicket::getTimeOfficeDeparture,TripTicketDTO::setTimeOfficeDeparture))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTicket::getTimePlaceArrival,TripTicketDTO::setTimePlaceArrival))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTicket::getTimePlaceDeparture,TripTicketDTO::setTimePlaceDeparture))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTicket::getTimeOfficeArrival,TripTicketDTO::setTimeOfficeArrival));

    return modelMapper.map(tripTicket,TripTicketDTO.class);
    }


    private TripTicket convertToModel(TripTicketDTO tripTicketDTO){
        Converter<String,LocalDate> toLocalDate=
        context -> context.getSource() == null ? null : LocalDate.parse(context.getSource(),dateFormatter);

        Converter<String, LocalTime> toLocalTime =
        context -> context.getSource() == null ? null : LocalTime.parse(context.getSource(),timeFormatter);

        modelMapper.typeMap(TripTicketDTO.class, TripTicket.class)
                .addMappings(mapping -> mapping.using(toLocalDate).map(TripTicketDTO::getDateDeparture, TripTicket::setDateDeparture))
                .addMappings(mapping -> mapping.using(toLocalDate).map(TripTicketDTO::getDateReturn, TripTicket::setDateReturn))
                .addMappings(mapping -> mapping.using(toLocalTime).map(TripTicketDTO::getTimeOfficeDeparture, TripTicket::setTimeOfficeDeparture))
                .addMappings(mapping -> mapping.using(toLocalTime).map(TripTicketDTO::getTimePlaceArrival, TripTicket::setTimePlaceArrival))
                .addMappings(mapping -> mapping.using(toLocalTime).map(TripTicketDTO::getTimePlaceDeparture, TripTicket::setTimePlaceDeparture))
                .addMappings(mapping -> mapping.using(toLocalTime).map(TripTicketDTO::getTimeOfficeArrival, TripTicket::setTimeOfficeArrival));

        return modelMapper.map(tripTicketDTO,TripTicket.class);
    }



    @Override
    public List<TripTicketDTO> findAll() {
        return tripTicketRepository.findAll().stream().map(this::convertToDTO).toList();

    }

    @Override
    public List<TripTicketDTO> findByMonthAndYear(Integer month, Integer year) {
        return tripTicketRepository.findByMonthAndYear(month,year).stream().map(this::convertToDTO).toList();
    }

    @Override
    public TripTicketDTO findByDateDepartureAndDateReturn(String dateDeparture, String dateReturn) {
        LocalDate localDateDeparture = LocalDate.parse(dateDeparture,dateFormatter);
        LocalDate localDateReturn = LocalDate.parse(dateReturn,dateFormatter);

    TripTicket tripTicket = tripTicketRepository.findByDateDepartureAndDateReturn(localDateDeparture,localDateReturn)
                .orElseThrow(() -> {

                    String message = (dateDeparture.equals(dateReturn)) ? String.format("No Trip Ticket found with this following date: %s",dateDeparture)
                            : String.format("No Trip Ticket found with these following dates: departure: %s , return : %s",dateDeparture, dateReturn);

                    return new ResourceNotFoundException(message);
                });


    return this.convertToDTO(tripTicket);
    }

    @Override
    public TripTicketDTO findById(Long id) {
        TripTicket tripTicket =  tripTicketRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TripTicket","id",id));
        return this.convertToDTO(tripTicket);
    }

    @Override
    public List<TripTicketDTO> findTripTicketAndPlacesById(Long id) {
        return tripTicketRepository.findTripTicketAndPlacesById(id).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TripTicketDTO> findByPlace(Long id) {
        return tripTicketRepository.findByPlace(id).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TripTicketDTO> findByBuildingName(String buildingName) {
        return tripTicketRepository.findTripTicketAndPlacesByBuildingName(buildingName).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TripTicketDTO> findByDefaultPlace(String defaultPlace) {
        return tripTicketRepository.findTripTicketAndPlacesByDefaultPlace(defaultPlace).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TripTicketDTO> findByBarangay(String barangay) {
        return tripTicketRepository.findTripTicketAndPlacesByBarangayName(barangay).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TripTicketDTO> findByMunicipality(String municipality) {
        return tripTicketRepository.findTripTicketAndPlacesByMunicipalityName(municipality).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TripTicketDTO> findByProvince(String province) {
        return tripTicketRepository.findTripTicketAndPlacesByProvinceName(province).stream().map(this::convertToDTO).toList();
    }

    @Override
    @Transactional
    public TripTicketDTO add(TripTicketDTO tripTicketDTO) {

        TripTicket tripTicket = this.convertToModel(tripTicketDTO);
        FuelBalance fuelBalance = fuelBalanceService.findFuelBalance();
        tripTicket.setFuelBalance(fuelBalance.getFuel());

        Distance distance = distanceService.findDistance();
        tripTicket.setStartDistance(distance.getDistance());

        TripTicket dbTripTicket = tripTicketRepository.save(tripTicket) ;
        tripTicketDTO.setId(dbTripTicket.getId());
        return tripTicketDTO;
    }

    @Override
    @Transactional
    public TripTicketDTO update(TripTicketDTO tripTicketDTO, Long id) {
        TripTicket tripTicket = tripTicketRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TripTicket","id",id));
        tripTicket.setDriver(modelMapper.map(tripTicketDTO.getEmployee(), Driver.class));
        tripTicket.setDateDeparture(DateTimeConverter.convertToLocalDate(tripTicketDTO.getDateDeparture()));
        tripTicket.setDateReturn(DateTimeConverter.convertToLocalDate(tripTicketDTO.getDateReturn()));
        tripTicket.setPlaces(tripTicketDTO.getPlaces().stream().map(p->modelMapper.map(p, Place.class)).toList());
        tripTicket.setPassengers(tripTicketDTO.getPassengers().stream().map(p->modelMapper.map(p, Passenger.class)).toList());
        tripTicket.setTimeOfficeDeparture(DateTimeConverter.convertToLocalTime(tripTicketDTO.getTimeOfficeDeparture()));
        tripTicket.setTimePlaceArrival(DateTimeConverter.convertToLocalTime(tripTicketDTO.getTimePlaceArrival()));
        tripTicket.setTimeOfficeDeparture(DateTimeConverter.convertToLocalTime(tripTicketDTO.getTimePlaceArrival()));
        tripTicket.setFuelBalance(tripTicketDTO.getFuelBalance());
        tripTicket.setIssuedFuel(tripTicketDTO.getIssuedFuel());
        tripTicket.setPurchasedFuel(tripTicketDTO.getPurchasedFuel());
        tripTicket.setConsumedFuel(tripTicketDTO.getConsumedFuel());
        tripTicket.setRemainingFuel(tripTicketDTO.getRemainingFuel());
        tripTicket.setGearOil(tripTicketDTO.getGearOil());
        tripTicket.setLubricantOil(tripTicketDTO.getLubricantOil());
        tripTicket.setStartDistance(tripTicketDTO.getStartDistance());
        tripTicket.setEndDistance(tripTicketDTO.getEndDistance());
        tripTicket.setDistanceTravelled(tripTicketDTO.getDistanceTravelled());
        tripTicket.setRemarks(tripTicketDTO.getRemarks());
        tripTicket.setPurpose(modelMapper.map(tripTicketDTO.getPurpose(), Purpose.class));
        tripTicket.setVehicle(modelMapper.map(tripTicketDTO.getVehicle(), Vehicle.class));
        tripTicketRepository.save(tripTicket);
        fuelBalanceService.update(tripTicketDTO.getRemainingFuel());
        distanceService.update(tripTicketDTO.getEndDistance());
        tripTicketDTO.setId(tripTicket.getId());

        return tripTicketDTO;
    }

    @Override
    public void delete(Long id) {
        tripTicketRepository.deleteById(id);
    }
}
