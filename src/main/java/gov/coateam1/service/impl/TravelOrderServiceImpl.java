package gov.coateam1.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import gov.coateam1.constants.AppConstant;
import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.TravelOrderMapper;
import gov.coateam1.model.Purpose;
import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.*;
import gov.coateam1.payload.*;
import gov.coateam1.payload.place.*;
import gov.coateam1.repository.PurposeRepository;
import gov.coateam1.repository.ReportToRepository;
import gov.coateam1.repository.TravelOrderRepository;
import gov.coateam1.repository.VehicleRepository;
import gov.coateam1.repository.employee.EmployeeRepository;
import gov.coateam1.repository.place.*;
import gov.coateam1.service.TravelOrderService;
import gov.coateam1.util.DateTimeConverter;
import gov.coateam1.util.JSONDataLoader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;


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
    private final JSONDataLoader jsonDataLoader;
    private final RegionRepository regionRepository;


    @Override
    public List<TravelOrderDTO> findAll() {
        return travelOrderRepository.findAll().stream().map(travelOrderMapper::mapToDTO).toList();
    }

//    @Override
//    public TravelOrderDTO findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn) {
//        TravelOrder travelOrder = travelOrderRepository.findByDateDepartureAndDateReturn(dateDeparture, dateReturn)
//                .orElseThrow(() -> {
//                    String message = (dateDeparture.isEqual(dateReturn)) ? String.format("No Travel Order found with this following date: %s", dateTimeFormatter.format(dateDeparture))
//                            : String.format("No Travel Order found with these following dates: departure: %s , return : %s", dateTimeFormatter.format(dateDeparture), dateTimeFormatter.format(dateReturn));
//
//                    return new ResourceNotFoundException(message);
//                });
//
//
//        return travelOrderMapper.mapToDTO(travelOrder);
//    }

    @Override
    public TravelOrderDTO findById(Long id) {
        TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TravelOrder", "id", id));
        return travelOrderMapper.mapToDTO(travelOrder);
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
        return travelOrderMapper.mapToDTO(dbTravelOrder);
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

        return travelOrderMapper.mapToDTO(dbTravelOrder);

    }

    @Override
    public void delete(Long id) {
        travelOrderRepository.deleteById(id);
    }

    @Override
    public List<TravelOrder> findTravelOrderByEmployeeId(Long employeeId) {
        Optional<? extends Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        return employee.getTravelOrders();
    }

    private Set<Place> getConvertedPlaces(Set<PlaceDTO> placeDTOs) throws Exception {
        Set<Place> places = new LinkedHashSet<>();
        for (PlaceDTO placeDTO : placeDTOs) {
            Optional<Place> placeOptional = placeRepository.findById(placeDTO.getId());
            Place place = null;
            if (placeOptional.isPresent()) {
                place = placeOptional.get();
                place.setBuildingName(placeDTO.getBuildingName());
            } else {
                if (placeDTO.getDefaultPlace() == null) {
                    Optional<Place> optionalPlace = placeRepository.findPlaceByCodes(
                            placeDTO.getBarangay() == null ? null : placeDTO.getBarangay().getCode(),
                            placeDTO.getMunicipality().getCode(),
                            placeDTO.getProvince().getCode(),
                            placeDTO.getRegionDTO().getCode()
                    );
                    if (optionalPlace.isPresent()) {
                        place = optionalPlace.get();
                    } else {
                        place = new Place();
                        place.setBuildingName(placeDTO.getBuildingName());

                        if (placeDTO.getBarangay() == null) {
                            place.setBarangay(null);
                        } else {
                            BarangayDTO barangayDTO = jsonDataLoader.getFromCode(placeDTO.getBarangay().getCode(), AppConstant.BARANGAY_JSON, BarangayDTO.class);
                            Barangay barangay = new Barangay(barangayDTO.getCode(), barangayDTO.getName());
                            barangay.addPlace(place);
                            place.setBarangay(barangay);
                            barangayRepository.save(barangay);

                        }
                        MunicipalityDTO municipalityDTO = jsonDataLoader.getFromCode(placeDTO.getMunicipality().getCode(), AppConstant.MUNICIPALITY_JSON, MunicipalityDTO.class);
                        Municipality municipality = new Municipality(municipalityDTO.getCode(), municipalityDTO.getName());
                        municipality.addPlace(place);
                        municipalityRepository.save(municipality);

                        ProvinceDTO provinceDTO = jsonDataLoader.getFromCode(placeDTO.getProvince().getCode(), AppConstant.PROVINCE_JSON, ProvinceDTO.class);
                        Province province = new Province(provinceDTO.getCode(), provinceDTO.getName());
                        province.addPlace(place);
                        provinceRepository.save(province);

                        RegionDTO regionDTO = jsonDataLoader.getFromCode(placeDTO.getRegionDTO().getCode(), AppConstant.REGION_JSON, RegionDTO.class);
                        Region region = new Region(regionDTO.getCode(), regionDTO.getName());
                        region.addPlace(place);
                        regionRepository.save(region);
                    }

                } else {
                    place = placeRepository.findByDefaultPlace(placeDTO.getDefaultPlace()).orElse(new Place(placeDTO.getDefaultPlace()));
                }
            }

            places.add(place);
        }
        return places;
    }

    private Set<ReportTo> getConvertedReportTos(Set<ReportToDTO> reportToDTOs) {
        Set<ReportTo> reportTos = new LinkedHashSet<>();
        for (ReportToDTO reportToDTO : reportToDTOs) {
            ReportTo reportTo = reportToRepository.findById(reportToDTO.getId()).orElse(new ReportTo(reportToDTO.getName()));
            ReportTo dbReportTo = reportToRepository.save(reportTo);
            reportTos.add(dbReportTo);
        }
        return reportTos;
    }


}
