package gov.coateam1.mapper;


import gov.coateam1.functionalinterface.CheckedFunction;
import gov.coateam1.model.TripTicket;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.Place;
import gov.coateam1.payload.TripTicketDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TripTicketMapper {

    private final PlaceMapper placeMapper;
    private final VehicleMapper vehicleMapper;
    private final EmployeeMapper employeeMapper;
    private final PurposeMapper purposeMapper;



    public TripTicket mapToModel(TripTicketDTO tripTicketDTO) throws Exception {
        Driver driver = employeeMapper.maptoModel(tripTicketDTO.getEmployeeDTO(), Driver.class);
        List<Place> places = tripTicketDTO.getPlaceDTOs().stream().map(placeMapper::mapToModel).toList();
        List<Passenger> passengers = tripTicketDTO.getPassengers().stream().map(throwingFunction(emp->employeeMapper.maptoModel(emp,Passenger.class))).toList();



        return null;
    }


    public TripTicketDTO mapToDTO(TripTicket tripTicket){
        return null;
    }

    public TripTicket mapToModel(TripTicketDTO tripTicketDTO,TripTicket tripTicket){
        return null;
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
