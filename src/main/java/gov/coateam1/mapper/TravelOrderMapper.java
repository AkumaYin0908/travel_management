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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class TravelOrderMapper {

   private final PlaceMapper placeMapper;
   private final VehicleMapper vehicleMapper;
   private final ReportToMapper reportToMapper;
   private final PurposeMapper purposeMapper;
   private final ModelMapper modelMapper;

   private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
   public  TravelOrder mapToModel(TravelOrderDTO travelOrderDTO) {
      Purpose purpose = purposeMapper.mapToModel(travelOrderDTO.getPurpose());
      List<Place> places  = travelOrderDTO.getPlaces().stream().map(placeMapper::mapToModel).toList();
      List<ReportTo> reportTos = travelOrderDTO.getReportTos().stream().map(reportToMapper::mapToModel).toList();
      Vehicle vehicle = vehicleMapper.mapToModel(travelOrderDTO.getVehicle());


      return new TravelOrder(travelOrderDTO.getId(),
              LocalDate.parse(travelOrderDTO.getDateIssued(),dateTimeFormatter),LocalDate.parse(travelOrderDTO.getDateDeparture(),dateTimeFormatter),
              LocalDate.parse(travelOrderDTO.getDateReturn(),dateTimeFormatter),purpose,vehicle, reportTos,places,LocalDate.parse(travelOrderDTO.getLastTravel(),dateTimeFormatter));
   }

   public TravelOrderDTO mapToDTO(TravelOrder travelOrder){
      PurposeDTO purposeDTO = purposeMapper.mapToDTO(travelOrder.getPurpose());
      List<PlaceDTO> placeDTOS = travelOrder.getPlaces().stream().map(placeMapper::mapToDTO).toList();
      List<ReportToDTO> reportToDTOS = travelOrder.getReportTos().stream().map(reportToMapper::mapToDTO).toList();
      VehicleDTO vehicleDTO =vehicleMapper.mapToDTO(travelOrder.getVehicle());

      EmployeeDTO employeeDTO = modelMapper.map(travelOrder.getEmployee(), EmployeeDTO.class);

      return new TravelOrderDTO(travelOrder.getId(),
              employeeDTO,
              dateTimeFormatter.format(travelOrder.getDateIssued()),
              dateTimeFormatter.format(travelOrder.getDateDeparture()),
              dateTimeFormatter.format(travelOrder.getDateReturn()),
              purposeDTO,
              vehicleDTO,
              reportToDTOS,
              placeDTOS,
              dateTimeFormatter.format(travelOrder.getLastTravel()));



   }

   public void mapToModel(TravelOrderDTO travelOrderDTO, TravelOrder travelOrder) throws Exception {

      Purpose purpose = purposeMapper.mapToModel(travelOrderDTO.getPurpose());
      List<Place> places  = travelOrderDTO.getPlaces().stream().map(placeMapper::mapToModel).toList();
      List<ReportTo> reportTos = travelOrderDTO.getReportTos().stream().map(reportToMapper::mapToModel).toList();
      Vehicle vehicle = vehicleMapper.mapToModel(travelOrderDTO.getVehicle());

      travelOrder.setDateIssued(LocalDate.parse(travelOrderDTO.getDateIssued(),dateTimeFormatter));
      travelOrder.setDateDeparture(LocalDate.parse(travelOrderDTO.getDateDeparture(),dateTimeFormatter));
      travelOrder.setDateReturn(LocalDate.parse(travelOrderDTO.getDateReturn(),dateTimeFormatter));
      travelOrder.setPurpose(purpose);
      travelOrder.setVehicle(vehicle);
      travelOrder.setReportTos(reportTos);
      travelOrder.setPlaces(places);
      travelOrder.setLastTravel(LocalDate.parse(travelOrderDTO.getLastTravel(),dateTimeFormatter));

   }


}
