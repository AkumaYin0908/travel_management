package gov.coateam1.service.impl;


import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.TravelOrderMapper;
import gov.coateam1.model.Purpose;
import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.Barangay;
import gov.coateam1.model.place.Municipality;
import gov.coateam1.model.place.Place;
import gov.coateam1.model.place.Province;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.payload.ReportToDTO;
import gov.coateam1.payload.TravelOrderDTO;
import gov.coateam1.payload.VehicleDTO;
import gov.coateam1.payload.place.PlaceDTO;
import gov.coateam1.repository.PurposeRepository;
import gov.coateam1.repository.ReportToRepository;
import gov.coateam1.repository.TravelOrderRepository;
import gov.coateam1.repository.VehicleRepository;
import gov.coateam1.repository.employee.EmployeeRepository;
import gov.coateam1.repository.place.BarangayRepository;
import gov.coateam1.repository.place.MunicipalityRepository;
import gov.coateam1.repository.place.PlaceRepository;
import gov.coateam1.repository.place.ProvinceRepository;
import gov.coateam1.service.PurposeService;
import gov.coateam1.service.ReportToService;
import gov.coateam1.service.TravelOrderService;
import gov.coateam1.service.VehicleService;
import gov.coateam1.service.place.PlaceService;
import gov.coateam1.util.DateTimeConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class TravelOrderServiceImpl implements TravelOrderService {

    private final TravelOrderRepository travelOrderRepository;
    private final EmployeeRepository employeeRepository;
    private final TravelOrderMapper travelOrderMapper;
    private final ModelMapper modelMapper;
    private final VehicleRepository vehicleRepository;
    private final PurposeRepository purposeRepository;
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
    private final PlaceRepository placeRepository;
    private final ReportToRepository reportToRepository;
    private final BarangayRepository barangayRepository;
    private final MunicipalityRepository municipalityRepository;
    private final ProvinceRepository provinceRepository;


//    private TravelOrderDTO convertToDTO(TravelOrder travelOrder) {
//
//        Converter<LocalDate, String> toString =
//                ctx -> ctx.getSource() == null ? null : dateFormatter.format(ctx.getSource());
//
//        Converter<? extends  Employee,EmployeeDTO>converter = ctx ->ctx.getSource() ==
//                null ? null : modelMapper.map(ctx.getSource(),EmployeeDTO.class);
//
//
//
//
//        modelMapper.addConverter(converter);
//        modelMapper.typeMap(TravelOrder.class, TravelOrderDTO.class)
//                .addMappings(mapping -> mapping.using(toString).map(TravelOrder::getDateIssued, TravelOrderDTO::setDateIssued))
//                .addMappings(mapping -> mapping.using(toString).map(TravelOrder::getDateDeparture, TravelOrderDTO::setDateDeparture))
//                .addMappings(mapping -> mapping.using(toString).map(TravelOrder::getDateReturn, TravelOrderDTO::setDateReturn))
//                .addMappings(mapping -> mapping.using(toString).map(TravelOrder::getLastTravel, TravelOrderDTO::setLastTravel));
//
//        return modelMapper.map(travelOrder, TravelOrderDTO.class);
//
//    }
//
//    private TravelOrder convertToModel(TravelOrderDTO travelOrderDTO) {
//
//        Converter<EmployeeDTO,? extends Employee>converter = ctx ->ctx.getSource() ==
//                null ? null : modelMapper.map(ctx.getSource(),Employee.class);
//
//        Converter<String, LocalDate> toLocalDate =
//                ctx -> ctx.getSource() == null ? null : LocalDate.parse(ctx.getSource(), dateFormatter);
//
//        modelMapper.addConverter(converter);
//        modelMapper.typeMap(TravelOrderDTO.class, TravelOrder.class)
//                .addMappings(mapping -> mapping.using(toLocalDate).map(TravelOrderDTO::getDateIssued, TravelOrder::setDateIssued))
//                .addMappings(mapping -> mapping.using(toLocalDate).map(TravelOrderDTO::getDateDeparture, TravelOrder::setDateDeparture))
//                .addMappings(mapping -> mapping.using(toLocalDate).map(TravelOrderDTO::getDateReturn, TravelOrder::setDateReturn))
//                .addMappings(mapping -> mapping.using(toLocalDate).map(TravelOrderDTO::getLastTravel, TravelOrder::setLastTravel));
//
//
//        return modelMapper.map(travelOrderDTO, TravelOrder.class);
//
//    }


    @Override
    public List<TravelOrderDTO> findAll() {
        return travelOrderRepository.findAll().stream().map(travelOrderMapper::mapToDTO).toList();
    }

    @Override
    public TravelOrderDTO findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn) {
        TravelOrder travelOrder = travelOrderRepository.findByDateDepartureAndDateReturn(dateDeparture, dateReturn)
                .orElseThrow(() -> {
                    String message = (dateDeparture.isEqual(dateReturn)) ? String.format("No Travel Order found with this following date: %s", dateTimeFormatter.format(dateDeparture))
                            : String.format("No Travel Order found with these following dates: departure: %s , return : %s", dateTimeFormatter.format(dateDeparture), dateTimeFormatter.format(dateReturn));

                    return new ResourceNotFoundException(message);
                });


        return travelOrderMapper.mapToDTO(travelOrder);
    }

    @Override
    public TravelOrderDTO findById(Long id) {
        TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TravelOrder", "id", id));
        return travelOrderMapper.mapToDTO(travelOrder);
    }

    @Override
    @Transactional
    public TravelOrderDTO add(Long employeeId, TravelOrderDTO travelOrderDTO) throws Exception {
        Optional<? extends Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        TravelOrder travelOrder = new TravelOrder();

        Driver driver = null;
        Passenger passenger = null;
        if (optionalEmployee.isPresent()) {
            if (optionalEmployee.get().getEmployeeType().equals("DRIVER")) {
                driver = (Driver) optionalEmployee.get();
                driver.addTravelOrder(travelOrder);
            } else {
                passenger = (Passenger) optionalEmployee.get();
                passenger.addTravelOrder(travelOrder);
            }
        }


        travelOrder.setEmployee(driver == null ? passenger : driver);


        Purpose purpose = purposeRepository.findByPurpose(travelOrderDTO.getPurpose().getPurpose()).orElse(new Purpose(travelOrderDTO.getPurpose().getPurpose()));
        purpose.addTravelOrder(travelOrder);


        travelOrder.setDateIssued(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateIssued()));
        travelOrder.setDateDeparture(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateDeparture()));
        travelOrder.setDateReturn(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateReturn()));

        Vehicle vehicle = vehicleRepository.findById(travelOrderDTO.getVehicle().getId()).orElseThrow(()->new ResourceNotFoundException("Vehicle","id",travelOrderDTO.getVehicle().getId()));
        vehicle.addTravelOrder(travelOrder);



        for (PlaceDTO placeDTO : travelOrderDTO.getPlaces()) {
            Place place = placeRepository.findById(placeDTO.getId()).orElse(new Place());

            place.setBuildingName(placeDTO.getBuildingName());
            place.setDefaultPlace(placeDTO.getDefaultPlace());
            if(placeDTO.getBarangay() == null){
                place.setBarangay(null);
            }else{
                Barangay barangay = barangayRepository.getReferenceById(placeDTO.getBarangay().getId());
                barangay.addPlace(place);
                place.setBarangay(barangay);
            }

            Municipality municipality = municipalityRepository.getReferenceById(placeDTO.getMunicipality().getId());
            municipality.addPlace(place);
            Province province = provinceRepository.getReferenceById(placeDTO.getMunicipality().getId());
            province.addPlace(place);


            place.setMunicipality(municipality);
            place.setProvince(province);
            place.addTravelOrder(travelOrder);
            travelOrder.addPlace(place);
        }

        for (ReportToDTO reportToDTO : travelOrderDTO.getReportTos()) {
            ReportTo reportTo = reportToRepository.findById(reportToDTO.getId()).orElse(new ReportTo(reportToDTO.getName()));
            reportTo.addTravelOrder(travelOrder);
            travelOrder.addReportTo(reportTo);
        }

        travelOrder.setLastTravel(DateTimeConverter.convertToLocalDate(travelOrderDTO.getLastTravel()));

        TravelOrder dbTravelOrder = travelOrderRepository.save(travelOrder);

        return travelOrderMapper.mapToDTO(dbTravelOrder);
    }

    @Override
    @Transactional
    public TravelOrderDTO update(TravelOrderDTO travelOrderDTO, Long id, Long employeeId) throws Exception {
        TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TravelOrder", "id", id));

        Optional<? extends Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        Driver driver = null;
        Passenger passenger = null;
        if (optionalEmployee.isPresent()) {
            if (optionalEmployee.get().getEmployeeType().equals("DRIVER")) {
                driver = (Driver) optionalEmployee.get();
            } else {
                passenger = (Passenger) optionalEmployee.get();
            }
        }

        travelOrder.setEmployee(driver == null ? passenger : driver);

        travelOrder.setDateIssued(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateIssued()));
        travelOrder.setDateDeparture(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateDeparture()));
        travelOrder.setDateReturn(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateReturn()));
        travelOrder.setLastTravel(DateTimeConverter.convertToLocalDate(travelOrderDTO.getLastTravel()));

        Purpose purpose = modelMapper.map(travelOrderDTO.getPurpose(), Purpose.class);
        if (purpose.getId() == 0) {
            travelOrder.setPurpose(purposeRepository.save(purpose));
        } else {
            travelOrder.setPurpose(purpose);
        }


        for (PlaceDTO placeDTO : travelOrderDTO.getPlaces()) {
            Place place = modelMapper.map(placeDTO, Place.class);
            if (place.getId() == 0) {
                travelOrder.addPlace(placeRepository.save(place));
            } else {
                travelOrder.addPlace(place);
            }
        }

        for (ReportToDTO reportToDTO : travelOrderDTO.getReportTos()) {
            ReportTo reportTo = modelMapper.map(reportToDTO, ReportTo.class);
            if (reportTo.getId() == 0) {
                travelOrder.addReportTo(reportToRepository.save(reportTo));
            } else {
                travelOrder.addReportTo(reportTo);
            }
        }

        travelOrderRepository.save(travelOrder);
        travelOrderDTO.setId(id);
        return travelOrderDTO;

    }

//    @Override
//    public List<TravelOrderDTO> findTravelOrderAndReportTosById(Long id) {
//        return travelOrderRepository.findTravelOrderAndReportTosById(id).stream().map(travelOrderMapper::mapToDTO).toList();
//    }
//
//    @Override
//    public List<TravelOrderDTO> findTravelOrderAndPlacesById(Long id) {
//        return travelOrderRepository.findTravelOrderAndPlacesById(id).stream().map(travelOrderMapper::mapToDTO).toList();
//    }
//
//    @Override
//    public List<TravelOrderDTO> findByBuildingName(String buildingName) {
//        return travelOrderRepository.findTravelOrderAndPlacesByBuildingName(buildingName).stream().map(travelOrderMapper::mapToDTO).toList();
//    }
//
//    @Override
//    public List<TravelOrderDTO> findByBarangay(String barangay) {
//        return travelOrderRepository.findTravelOrderAndPlacesByBarangayName(barangay).stream().map(travelOrderMapper::mapToDTO).toList();
//    }
//
//    @Override
//    public List<TravelOrderDTO> findByMunicipality(String municipality) {
//        return travelOrderRepository.findTravelOrderAndPlacesByMunicipalityName(municipality).stream().map(travelOrderMapper::mapToDTO).toList();
//    }
//
//    @Override
//    public List<TravelOrderDTO> findByProvince(String province) {
//        return travelOrderRepository.findTravelOrderAndPlacesByProvinceName(province).stream().map(travelOrderMapper::mapToDTO).toList();
//    }
//
//    @Override
//    public LocalDate findByNameOrderByDateReturnDESC(String name, LocalDate dateReturn) {
//        return travelOrderRepository.findByNameOrderByDateReturnDESC(name).orElse(dateReturn);
//    }

    @Override
    public void delete(Long id) {
        travelOrderRepository.deleteById(id);
    }
}
