package gov.coateam1.mapper;


import gov.coateam1.functionalinterface.CheckedFunction;
import gov.coateam1.model.Purpose;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.trip_ticket.TripDistance;
import gov.coateam1.model.trip_ticket.TripFuel;
import gov.coateam1.model.trip_ticket.TripTicket;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.Place;
import gov.coateam1.model.trip_ticket.TripTime;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
import gov.coateam1.payload.trip_ticket.TripDistanceDTO;
import gov.coateam1.payload.trip_ticket.TripFuelDTO;
import gov.coateam1.payload.trip_ticket.TripTicketDTO;
import gov.coateam1.payload.trip_ticket.TripTimeDTO;
import gov.coateam1.util.DateTimeConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TripTicketMapper {

    private final PlaceMapper placeMapper;
    private final VehicleMapper vehicleMapper;
    private final EmployeeMapper employeeMapper;
    private final PurposeMapper purposeMapper;
    private final ModelMapper modelMapper;


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

        Converter<LocalTime,String> toTimeString =
                context -> context.getSource() == null ? null : DateTimeConverter.convertToString(context.getSource());

        modelMapper.typeMap(TripTimeDTO.class,TripTime.class)
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTimeDTO::getTimeOfficeDeparture,TripTime::setTimeOfficeDeparture))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTimeDTO::getTimePlaceArrival,TripTime::setTimePlaceArrival))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTimeDTO::getTimePlaceDeparture,TripTime::setTimePlaceDeparture))
                .addMappings(mapping -> mapping.using(toTimeString).map(TripTimeDTO::getTimeOfficeArrival,TripTime::setTimeOfficeArrival));

        return modelMapper.map(tripTimeDTO,TripTime.class);
    }

    public TripTicket mapToModel(TripTicketDTO tripTicketDTO) throws Exception {
        LocalDate dateDeparture = DateTimeConverter.convertToLocalDate(tripTicketDTO.getDateDeparture());
        LocalDate dateReturn = DateTimeConverter.convertToLocalDate(tripTicketDTO.getDateReturn());
        Driver driver = modelMapper.map(tripTicketDTO.getEmployee(),Driver.class);
        Set<Place> places = tripTicketDTO.getPlaces().stream().map(placeMapper::mapToModel).collect(Collectors.toSet());
      //  Set<Passenger> passengers = tripTicketDTO.getPassengers().stream().map(throwingFunction(emp->employeeMapper.maptoModel(emp,Passenger.class))).collect(Collectors.toSet());
        Set<Passenger> passengers = tripTicketDTO.getPassengers().stream().map(passengerDTO ->modelMapper.map(passengerDTO,Passenger.class)).collect(Collectors.toSet());
        TripTime tripTime = this.convertToModel(tripTicketDTO.getTripTime());
        TripFuel tripFuel = modelMapper.map(tripTicketDTO.getTripFuel(),TripFuel.class);
        TripDistance tripDistance = modelMapper.map(tripTicketDTO.getTripDistance(),TripDistance.class);
        Purpose purpose = modelMapper.map(tripTicketDTO.getPurpose(),Purpose.class);
        Vehicle vehicle = modelMapper.map(tripTicketDTO.getVehicle(), Vehicle.class);



        return new TripTicket(
                tripTicketDTO.getId(), driver, dateDeparture, dateReturn, places, passengers, tripTime, tripFuel,
                tripTicketDTO.getGearOil(), tripTicketDTO.getLubricantOil(), tripDistance, tripTicketDTO.getRemarks(),
                purpose,vehicle);
    }


    public TripTicketDTO mapToDTO(TripTicket tripTicket){
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



    public static  <T,R,E extends Exception>Function<T,R> throwingFunction(CheckedFunction<T,R,E> checkedFunction){

        return empDTO->{
            try {
                return checkedFunction.apply(empDTO);
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        };
    }
}
