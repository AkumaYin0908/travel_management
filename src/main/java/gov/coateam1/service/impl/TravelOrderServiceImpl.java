package gov.coateam1.service.impl;


import gov.coateam1.constants.AppConstant;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.functionalinterface.ThrowFunction;
import gov.coateam1.mapper.PlaceMapper;
import gov.coateam1.model.Purpose;
import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.*;
import gov.coateam1.payload.*;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.payload.place.*;
import gov.coateam1.repository.PurposeRepository;
import gov.coateam1.repository.ReportToRepository;
import gov.coateam1.repository.TravelOrderRepository;
import gov.coateam1.repository.VehicleRepository;
import gov.coateam1.repository.employee.EmployeeRepository;
import gov.coateam1.service.TravelOrderService;
import gov.coateam1.service.place.PlaceService;
import gov.coateam1.util.DateTimeConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TravelOrderServiceImpl implements TravelOrderService {

    private final TravelOrderRepository travelOrderRepository;
    private final EmployeeRepository<Employee> employeeRepository;
    private final PlaceMapper placeMapper;
    private final VehicleRepository vehicleRepository;
    private final PurposeRepository purposeRepository;
    private final ModelMapper modelMapper;
    private final PlaceService placeService;
    private final ReportToRepository reportToRepository;


    @Override
    public List<TravelOrderDTO> findAll() {
        return travelOrderRepository.findAll().stream().map(ThrowFunction.throwingFunction(this::mapToDTO)).toList();
    }


    @Override
    public TravelOrderDTO findById(Long id) throws Exception {
        TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TravelOrder", "id", id));
        return this.mapToDTO(travelOrder);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TravelOrderDTO add(Long employeeId, TravelOrderDTO travelOrderDTO) throws Exception {

        TravelOrder travelOrder = new TravelOrder();

        Optional<? extends Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        Driver driver = null;
        Passenger passenger = null;
        if (employee.getEmployeeType().equals("DRIVER")) {
            driver = (Driver) employee;
        } else {
            passenger = (Passenger) employee;
        }

        travelOrder.setEmployee(driver == null ? passenger : driver);


        Purpose purpose = purposeRepository.findByPurpose(travelOrderDTO.getPurpose().getPurpose()).orElse(new Purpose(travelOrderDTO.getPurpose().getPurpose()));
        purpose.addTravelOrder(travelOrder);
        purposeRepository.save(purpose);

        travelOrder.setDateIssued(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateIssued()));
        travelOrder.setDateDeparture(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateDeparture()));
        travelOrder.setDateReturn(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateReturn()));

        Vehicle vehicle = vehicleRepository.findById(travelOrderDTO.getVehicle().getId()).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", travelOrderDTO.getVehicle().getId()));
        vehicle.addTravelOrder(travelOrder);

        travelOrder.setPlaces(getConvertedPlaces(travelOrderDTO.getPlaces()));

        travelOrder.setReportTos(getConvertedReportTos(travelOrderDTO.getReportTos()));

        travelOrder.setLastTravel(DateTimeConverter.convertToLocalDate(travelOrderDTO.getLastTravel()));

        TravelOrder dbTravelOrder = travelOrderRepository.save(travelOrder);
        return this.mapToDTO(dbTravelOrder);
    }

    @Override
    @Transactional
    public TravelOrderDTO update(TravelOrderDTO travelOrderDTO, Long id) throws Exception {
        TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TravelOrder", "id", id));
        Purpose purpose = purposeRepository.findByPurpose(travelOrderDTO.getPurpose().getPurpose()).orElse(new Purpose(travelOrderDTO.getPurpose().getPurpose()));
        purpose.addTravelOrder(travelOrder);
        purposeRepository.save(purpose);

        travelOrder.setDateIssued(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateIssued()));
        travelOrder.setDateDeparture(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateDeparture()));
        travelOrder.setDateReturn(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateReturn()));

        Vehicle vehicle = vehicleRepository.findById(travelOrderDTO.getVehicle().getId()).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", travelOrderDTO.getVehicle().getId()));
        vehicle.addTravelOrder(travelOrder);

        travelOrder.setPlaces(getConvertedPlaces(travelOrderDTO.getPlaces()));

        travelOrder.setReportTos(getConvertedReportTos(travelOrderDTO.getReportTos()));

        travelOrder.setLastTravel(DateTimeConverter.convertToLocalDate(travelOrderDTO.getLastTravel()));

        TravelOrder dbTravelOrder = travelOrderRepository.save(travelOrder);

        return this.mapToDTO(dbTravelOrder);

    }

    @Override
    public void delete(Long id) {
        travelOrderRepository.deleteById(id);
    }

    @Override
    public List<TravelOrderDTO> findTravelOrderByEmployeeId(Long employeeId) {
        Optional<? extends Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        return employee.getTravelOrders().stream().map(ThrowFunction.throwingFunction(this::mapToDTO)).toList();
    }

    @Override
    public List<TravelOrderDTO> findTravelOrderByDateIssued(String strDateIssued) {
        LocalDate dateIssued = DateTimeConverter.convertToLocalDate(strDateIssued);
        List<TravelOrder> travelOrders = travelOrderRepository.findByDateIssued(dateIssued);
        return travelOrders.stream().map(ThrowFunction.throwingFunction(this::mapToDTO)).toList();
    }

    @Transactional
    private Set<Place> getConvertedPlaces(Set<PlaceDTO> placeDTOs) throws Exception {
        Set<Place> places = new LinkedHashSet<>();
        for (PlaceDTO placeDTO : placeDTOs) {
            Place place;
            if(placeDTO.getDefaultPlace().equals("N/A")){
                place = placeMapper.mapToModel(placeService.findPlaceByCodes(placeDTO));
            }else{
                place = placeMapper.mapToModel(placeService.findByDefaultPlace(placeDTO.getDefaultPlace()));
            }
            places.add(place);

        }
        return places;
    }

    private Set<ReportTo> getConvertedReportTos(Set<ReportToDTO> reportToDTOs) {
        Set<ReportTo> reportTos = new LinkedHashSet<>();
        for (ReportToDTO reportToDTO : reportToDTOs) {
            Optional<ReportTo> reportToOptional = reportToRepository.findById(reportToDTO.getId());
            ReportTo reportTo = reportToOptional.orElseGet(() -> new ReportTo(reportToDTO.getName()));
            ReportTo dbReportTo=reportToRepository.save(reportTo);
            reportTos.add(dbReportTo);
        }
        return reportTos;
    }


    public TravelOrderDTO mapToDTO(TravelOrder travelOrder) {
        PurposeDTO purposeDTO = modelMapper.map(travelOrder.getPurpose(),PurposeDTO.class);
        Set<PlaceDTO> placeDTOS = travelOrder.getPlaces().stream().map(ThrowFunction.throwingFunction(placeMapper::mapToDTO)).collect(Collectors.toSet());
        Set<ReportToDTO> reportToDTOS = travelOrder.getReportTos().stream().map(reportTo -> modelMapper.map(reportTo, ReportToDTO.class)).collect(Collectors.toSet());
        VehicleDTO vehicleDTO = modelMapper.map(travelOrder.getVehicle(), VehicleDTO.class);
        EmployeeDTO employeeDTO = modelMapper.map(travelOrder.getEmployee(), EmployeeDTO.class);

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
