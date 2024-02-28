package gov.coateam1.mapper;


import gov.coateam1.model.Purpose;
import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.place.Place;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.payload.ReportToDTO;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.PlaceDTO;
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

   private final PlaceMapper placeMapper;
   private final VehicleMapper vehicleMapper;
   private final EmployeeMapper employeeMapper;
   private final ReportToMapper reportToMapper;
   private final PurposeMapper purposeMapper;


   public TravelOrder mapToModel(TravelOrderDTO travelOrderDTO) throws Exception {
      Employee employee = employeeMapper.maptoModel(travelOrderDTO.getEmployeeDTO(), Driver.class);
      Purpose purpose = purposeMapper.mapToModel(travelOrderDTO.getPurposeDTO());
      List<Place> places  = travelOrderDTO.getPlaceDTOs().stream().map(placeMapper::mapToModel).toList();
      List<ReportTo> reportTos = travelOrderDTO.getReportTos().stream().map(reportToMapper::mapToModel).toList();
      Vehicle vehicle = vehicleMapper.mapToModel(travelOrderDTO.getVehicleDTO());


      return new TravelOrder(travelOrderDTO.getId(),employee,
              travelOrderDTO.getDateIssued(),travelOrderDTO.getDateDeparture(),
              travelOrderDTO.getDateReturn(),purpose,vehicle, reportTos,places,travelOrderDTO.getLastTravel());
   }

   public TravelOrderDTO mapToDTO(TravelOrder travelOrder){
      EmployeeDTO employeeDTO=employeeMapper.mapToDTO(travelOrder.getEmployee());
      PurposeDTO purposeDTO = purposeMapper.mapToDTO(travelOrder.getPurpose());
      List<PlaceDTO> placeDTOS = travelOrder.getPlaces().stream().map(placeMapper::mapToDTO).toList();
      List<ReportToDTO> reportToDTOS = travelOrder.getReportTos().stream().map(reportToMapper::mapToDTO).toList();
      VehicleDTO vehicleDTO =vehicleMapper.mapToDTO(travelOrder.getVehicle());

      return new TravelOrderDTO(travelOrder.getId(),employeeDTO,travelOrder.getDateIssued(),
              travelOrder.getDateDeparture(),travelOrder.getDateReturn(),purposeDTO,vehicleDTO,reportToDTOS,placeDTOS,travelOrder.getLastTravel());



   }

   public void mapToModel(TravelOrderDTO travelOrderDTO, TravelOrder travelOrder) throws Exception {

      Employee employee = employeeMapper.maptoModel(travelOrderDTO.getEmployeeDTO(),Driver.class);
      Purpose purpose = purposeMapper.mapToModel(travelOrderDTO.getPurposeDTO());
      List<Place> places  = travelOrderDTO.getPlaceDTOs().stream().map(placeMapper::mapToModel).toList();
      List<ReportTo> reportTos = travelOrderDTO.getReportTos().stream().map(reportToMapper::mapToModel).toList();
      Vehicle vehicle = vehicleMapper.mapToModel(travelOrderDTO.getVehicleDTO());

      travelOrder.setEmployee(employee);
      travelOrder.setDateIssued(travelOrderDTO.getDateIssued());
      travelOrder.setDateDeparture(travelOrderDTO.getDateDeparture());
      travelOrder.setDateReturn(travelOrderDTO.getDateReturn());
      travelOrder.setPurpose(purpose);
      travelOrder.setVehicle(vehicle);
      travelOrder.setReportTos(reportTos);
      travelOrder.setPlaces(places);
      travelOrder.setLastTravel(travelOrderDTO.getLastTravel());

   }


}
