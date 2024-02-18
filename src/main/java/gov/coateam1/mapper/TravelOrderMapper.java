package gov.coateam1.mapper;


import gov.coateam1.model.Purpose;
import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.place.Place;
import gov.coateam1.payload.EmployeeDTO;
import gov.coateam1.payload.PlaceDTO;
import gov.coateam1.payload.TravelOrderDTO;
import gov.coateam1.service.PurposeService;
import gov.coateam1.service.VehicleService;
import gov.coateam1.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TravelOrderMapper {

   private final EmployeeService employeeService;
   private final VehicleService vehicleService;
   private final PurposeService purposeService;
   private EmployeeMapper<? extends Employee> employeeMapper;
   private PlaceMapper placeMapper;
   private VehicleMapper vehicleMapper;


   public TravelOrder mapToModel(TravelOrderDTO travelOrderDTO) throws Exception {
      EmployeeDTO employeeDTO = employeeService.findByName(travelOrderDTO.getEmployeeName());
      Employee employee = employeeMapper.maptoModel(employeeDTO);
      Purpose purpose = purposeService.findByPurpose(travelOrderDTO.getPurpose());
      List<Place> places  = travelOrderDTO.getPlaceDTOs().stream().map(placeMapper::mapToModel).toList();
      List<ReportTo> reportTos = travelOrderDTO.getReportTos();
      Vehicle vehicle = vehicleMapper.mapToModel(vehicleService.findByPlateNo(travelOrderDTO.getPlateNo()));




      return new TravelOrder(travelOrderDTO.getId(),employee,
              travelOrderDTO.getDateIssued(),travelOrderDTO.getDateDeparture(),
              travelOrderDTO.getDateReturn(),purpose,vehicle, reportTos,places,travelOrderDTO.getLastTravel());
   }

//   public TravelOrderDTO mapToDTO(TravelOrder travelOrder){
//
//      String employeeName = travelOrder.getEmployee().getName();
//
//      return new TravelOrderDTO(travelOrder.getId(),travelOrder)
//   }


}
