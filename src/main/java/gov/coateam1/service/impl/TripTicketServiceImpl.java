package gov.coateam1.service.impl;

import gov.coateam1.constants.AppConstant;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.PlaceMapper;
import gov.coateam1.model.Purpose;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.*;
import gov.coateam1.model.trip_ticket.TripDistance;
import gov.coateam1.model.trip_ticket.TripFuel;
import gov.coateam1.model.trip_ticket.TripTicket;
import gov.coateam1.model.trip_ticket.TripTime;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.*;
import gov.coateam1.payload.trip_ticket.TripDistanceDTO;
import gov.coateam1.payload.trip_ticket.TripFuelDTO;
import gov.coateam1.payload.trip_ticket.TripTicketDTO;
import gov.coateam1.payload.trip_ticket.TripTimeDTO;
import gov.coateam1.repository.PurposeRepository;
import gov.coateam1.repository.TripTicketRepository;
import gov.coateam1.repository.VehicleRepository;
import gov.coateam1.repository.employee.EmployeeRepository;
import gov.coateam1.repository.place.*;
import gov.coateam1.service.TripTicketService;
import gov.coateam1.service.place.PlaceService;
import gov.coateam1.util.DateTimeConverter;
import gov.coateam1.util.JSONDataLoader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TripTicketServiceImpl implements TripTicketService {

    private final TripTicketRepository tripTicketRepository;
    private final EmployeeRepository employeeRepository;
    private final VehicleRepository vehicleRepository;
    private final PlaceMapper placeMapper;
    private final ModelMapper modelMapper;
    private final PlaceService placeService;
    private final PurposeRepository purposeRepository;


    @Override
    public List<TripTicketDTO> findAll() {
        return tripTicketRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public TripTicketDTO findById(Long id) {
        TripTicket tripTicket = tripTicketRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TripTicket","id",id));
        return this.mapToDTO(tripTicket);
    }

    @Override
    public TripTicketDTO add(Long driverId, TripTicketDTO tripTicketDTO) throws Exception {
        TripTicket tripTicket = new TripTicket();

        Optional<Driver> optionalDriver = employeeRepository.findById(driverId);
        Driver driver = optionalDriver.orElseThrow(()->new ResourceNotFoundException("Employee","id",driverId));

        tripTicket.setDriver(driver);

        tripTicket.setDateDeparture(DateTimeConverter.convertToLocalDate(tripTicketDTO.getDateDeparture()));
        tripTicket.setDateReturn(DateTimeConverter.convertToLocalDate(tripTicketDTO.getDateReturn()));

        tripTicket.setPlaces(getConvertedPlaces(tripTicketDTO.getPlaces()));

        tripTicket.setPassengers(getConvertedPassengers(tripTicketDTO.getPassengers()));

        TripTime tripTime = this.convertToModel(tripTicketDTO.getTripTime());
        tripTicket.setTripTime(tripTime);


        TripFuel tripFuel = modelMapper.map(tripTicketDTO.getTripFuel(),TripFuel.class);
        tripTicket.setTripFuel(tripFuel);

        tripTicket.setGearOil(tripTicketDTO.getGearOil());

        tripTicket.setLubricantOil(tripTicketDTO.getLubricantOil());

        TripDistance tripDistance = modelMapper.map(tripTicketDTO.getTripDistance(),TripDistance.class);
        tripTicket.setTripDistance(tripDistance);

        tripTicket.setRemarks(tripTicketDTO.getRemarks());

        Purpose purpose = purposeRepository.findByPurpose(tripTicketDTO.getPurpose().getPurpose()).orElse(new Purpose(tripTicket.getPurpose().getPurpose()));
        purpose.addTripTicket(tripTicket);
        purposeRepository.save(purpose);

        Vehicle vehicle = vehicleRepository.
                findById(tripTicket.getVehicle().getId()).orElseThrow(()->new ResourceNotFoundException("Vehicle","id",tripTicket.getVehicle().getId()));
        tripTicket.setVehicle(vehicle);

        TripTicket dbTripTicket = tripTicketRepository.save(tripTicket);
        return this.mapToDTO(dbTripTicket);
    }

    @Override
    public TripTicketDTO update(TripTicketDTO tripTicketDTO, Long id) throws Exception {
        TripTicket tripTicket = tripTicketRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TripTicket","id",id));

        tripTicket.setDateDeparture(DateTimeConverter.convertToLocalDate(tripTicketDTO.getDateDeparture()));
        tripTicket.setDateReturn(DateTimeConverter.convertToLocalDate(tripTicketDTO.getDateReturn()));

        tripTicket.setPlaces(getConvertedPlaces(tripTicketDTO.getPlaces()));

        tripTicket.setPassengers(getConvertedPassengers(tripTicketDTO.getPassengers()));

        TripTime tripTime = this.convertToModel(tripTicketDTO.getTripTime());
        tripTicket.setTripTime(tripTime);

        TripFuel tripFuel = modelMapper.map(tripTicketDTO.getTripFuel(),TripFuel.class);
        tripTicket.setTripFuel(tripFuel);

        tripTicket.setGearOil(tripTicketDTO.getGearOil());

        tripTicket.setLubricantOil(tripTicketDTO.getLubricantOil());

        TripDistance tripDistance = modelMapper.map(tripTicketDTO.getTripDistance(),TripDistance.class);
        tripTicket.setTripDistance(tripDistance);

        tripTicket.setRemarks(tripTicketDTO.getRemarks());

        Purpose purpose = purposeRepository.findByPurpose(tripTicketDTO.getPurpose().getPurpose()).orElse(new Purpose(tripTicket.getPurpose().getPurpose()));
        purpose.addTripTicket(tripTicket);
        purposeRepository.save(purpose);

        Vehicle vehicle = vehicleRepository.
                findById(tripTicket.getVehicle().getId()).orElseThrow(()->new ResourceNotFoundException("Vehicle","id",tripTicket.getVehicle().getId()));
        tripTicket.setVehicle(vehicle);

        TripTicket dbTripTicket = tripTicketRepository.save(tripTicket);
        return this.mapToDTO(dbTripTicket);
    }

    @Override
    public void delete(Long id) {
        tripTicketRepository.deleteById(id);
    }

    @Transactional
    private Set<Place> getConvertedPlaces(Set<PlaceDTO> placeDTOs) throws Exception {
        Set<Place> places = new LinkedHashSet<>();
        for (PlaceDTO placeDTO : placeDTOs) {
            Place place = placeMapper.mapToModel(placeService.findPlaceByCodes(placeDTO));
            places.add(place);

        }
        return places;
    }



    private Set<Passenger> getConvertedPassengers(Set<EmployeeDTO> employeeDTOs){
        Set<Passenger> passengers = new LinkedHashSet<>();
        for(EmployeeDTO employeeDTO : employeeDTOs){
            Optional<Passenger> optionalPassenger = employeeRepository.findById(employeeDTO.getId());
            Passenger passenger = optionalPassenger.orElseThrow(()->new ResourceNotFoundException("Employee","id",employeeDTO.getName()));
            passengers.add(passenger);
        }

        return  passengers;
    }

    private TripTimeDTO convertToDTO(TripTime tripTime){

        Converter<LocalTime,String> toTimeString =
                context -> context.getSource() == null ? null : DateTimeConverter.convertToString(context.getSource());

        modelMapper.typeMap(TripTime.class, TripTimeDTO.class)
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTime::getTimeOfficeDeparture,TripTimeDTO::setTimeOfficeDeparture))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTime::getTimePlaceArrival,TripTimeDTO::setTimePlaceArrival))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTime::getTimePlaceDeparture,TripTimeDTO::setTimePlaceDeparture))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTime::getTimeOfficeArrival,TripTimeDTO::setTimeOfficeArrival));

        return modelMapper.map(tripTime,TripTimeDTO.class);
    }


    private TripTime convertToModel(TripTimeDTO tripTimeDTO){


        Converter<String, LocalTime> toLocalTime =
                context -> context.getSource() == null ? null : DateTimeConverter.convertToLocalTime(context.getSource());

        modelMapper.typeMap(TripTimeDTO.class,TripTime.class)
                .addMappings(mapping -> mapping.using(toLocalTime).map(TripTimeDTO::getTimeOfficeDeparture,TripTime::setTimeOfficeDeparture))
                .addMappings(mapping -> mapping.using(toLocalTime).map(TripTimeDTO::getTimePlaceArrival,TripTime::setTimePlaceArrival))
                .addMappings(mapping -> mapping.using(toLocalTime).map(TripTimeDTO::getTimePlaceDeparture,TripTime::setTimePlaceDeparture))
                .addMappings(mapping -> mapping.using(toLocalTime).map(TripTimeDTO::getTimeOfficeArrival,TripTime::setTimeOfficeArrival));

        return modelMapper.map(tripTimeDTO,TripTime.class);
    }

    private TripTicketDTO mapToDTO(TripTicket tripTicket){
        String strDateDeparture = DateTimeConverter.convertToString(tripTicket.getDateDeparture());
        String strDateReturn = DateTimeConverter.convertToString(tripTicket.getDateReturn());
        EmployeeDTO employeeDTO = modelMapper.map(tripTicket.getDriver(), EmployeeDTO.class);
        Set<PlaceDTO> placeDTOs = tripTicket.getPlaces().stream().map(place -> modelMapper.map(place, PlaceDTO.class)).collect(Collectors.toSet());
        Set<EmployeeDTO> employeeDTOs = tripTicket.getPassengers().stream().map(passenger -> modelMapper.map(passenger, EmployeeDTO.class)).collect(Collectors.toSet());
        TripTimeDTO tripTimeDTO = this.convertToDTO(tripTicket.getTripTime());
        TripFuelDTO tripFuelDTO = modelMapper.map(tripTicket.getTripFuel(),TripFuelDTO.class);
        TripDistanceDTO tripDistanceDTO = modelMapper.map(tripTicket.getTripDistance(),TripDistanceDTO.class);
        PurposeDTO purposeDTO = modelMapper.map(tripTicket.getPurpose(),PurposeDTO.class);
        VehicleDTO vehicleDTO = modelMapper.map(tripTicket.getVehicle(),VehicleDTO.class);

        return new TripTicketDTO(
                tripTicket.getId(),employeeDTO,strDateDeparture,strDateReturn,tripTimeDTO,
                placeDTOs,employeeDTOs,tripFuelDTO,tripTicket.getGearOil(),tripTicket.getLubricantOil(),
                tripDistanceDTO,tripTicket.getRemarks(),purposeDTO,vehicleDTO);
    }
}
