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
import gov.coateam1.util.DateTimeConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class TravelOrderMapper {

   private final PlaceMapper placeMapper;
   private final ModelMapper modelMapper;

   public  TravelOrder mapToModel(TravelOrderDTO travelOrderDTO) {
      Purpose purpose = modelMapper.map(travelOrderDTO.getPurpose(),Purpose.class);
      Set<Place> places  = travelOrderDTO.getPlaces().stream().map(placeMapper::mapToModel).collect(Collectors.toSet());
      Set<ReportTo> reportTos = travelOrderDTO.getReportTos().stream().map(reportToDTO -> modelMapper.map(reportToDTO,ReportTo.class)).collect(Collectors.toSet());
      Vehicle vehicle = modelMapper.map(travelOrderDTO.getVehicle(),Vehicle.class);


      return new TravelOrder(travelOrderDTO.getId(),
              DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateIssued()),DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateDeparture()),
              DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateReturn()),purpose,vehicle, reportTos,places,DateTimeConverter.convertToLocalDate(travelOrderDTO.getLastTravel()));
   }

   public TravelOrderDTO mapToDTO(TravelOrder travelOrder){
      PurposeDTO purposeDTO = modelMapper.map(travelOrder.getPurpose(),PurposeDTO.class);
      Set<PlaceDTO> placeDTOS = travelOrder.getPlaces().stream().map(placeMapper::mapToDTO).collect(Collectors.toSet());
      Set<ReportToDTO> reportToDTOS = travelOrder.getReportTos().stream().map(reportTo -> modelMapper.map(reportTo, ReportToDTO.class)).collect(Collectors.toSet());
      VehicleDTO vehicleDTO = modelMapper.map(travelOrder.getVehicle(), VehicleDTO.class);
      EmployeeDTO employeeDTO = modelMapper.map(travelOrder.getEmployee(), EmployeeDTO.class);
      System.out.println(travelOrder);
      return new TravelOrderDTO(travelOrder.getId(),
              employeeDTO,
              DateTimeConverter.convertToString(travelOrder.getDateIssued()),
              DateTimeConverter.convertToString(travelOrder.getDateDeparture()),
              DateTimeConverter.convertToString(travelOrder.getDateReturn()),
              purposeDTO,
              vehicleDTO,
              reportToDTOS,
              placeDTOS,
              DateTimeConverter.convertToString(travelOrder.getLastTravel()));

   }

}
