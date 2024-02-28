package gov.coateam1.mapper;


import gov.coateam1.model.TripTicket;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.payload.TripTicketDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripTicketMapper {

    private final PlaceMapper placeMapper;
    private final VehicleMapper vehicleMapper;
    private final EmployeeMapper employeeMapper;
    private final PurposeMapper purposeMapper;



    public TripTicket mapToModel(TripTicketDTO tripTicketDTO){
        return null;
    }


    public TripTicketDTO mapToDTO(TripTicket tripTicket){
        return null;
    }

    public TripTicket mapToModel(TripTicketDTO tripTicketDTO,TripTicket tripTicket){
        return null;
    }

    










}
