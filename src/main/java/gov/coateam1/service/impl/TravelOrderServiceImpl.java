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
import gov.coateam1.util.PlaceBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@RequiredArgsConstructor
public class TravelOrderServiceImpl implements TravelOrderService {

    private final TravelOrderRepository travelOrderRepository;
    private final EmployeeRepository employeeRepository;
    private final TravelOrderMapper travelOrderMapper;
    private final VehicleRepository vehicleRepository;
    private final PurposeRepository purposeRepository;
    private final ModelMapper modelMapper;
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
    public List<TravelOrderDTO> findTravelOrderByEmployeeId(Long employeeId) {
        Optional<? extends Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        List<TravelOrderDTO> travelOrderDTOs = employee.getTravelOrders().stream().map(travelOrderMapper::mapToDTO).toList();

        return travelOrderDTOs;
    }

    @Override
    public List<TravelOrderDTO> findTravelOrderByDateIssued(String strDateIssued) {
        LocalDate dateIssued = DateTimeConverter.convertToLocalDate(strDateIssued);
        List<TravelOrder> travelOrders = travelOrderRepository.findByDateIssued(dateIssued);
        return travelOrders.stream().map(travelOrderMapper::mapToDTO).toList();
    }


    private Set<Place> getConvertedPlaces(Set<PlaceDTO> placeDTOs) throws Exception {
        Set<Place> places = new LinkedHashSet<>();
        for (PlaceDTO placeDTO : placeDTOs) {
            Optional<Place> placeOptional = placeRepository.findById(placeDTO.getId());
            Place place = null;
            if (placeOptional.isPresent()) {
                place = placeOptional.get();
            } else {
                if (placeDTO.getDefaultPlace() == null || placeDTO.getDefaultPlace().equals("N/A")) {
                    Optional<Place> optionalPlaceWithoutBarangay = placeRepository.findPlaceByCodes(
                            placeDTO.getMunicipality().getCode(),
                            placeDTO.getProvince().getCode(),
                            placeDTO.getRegionDTO().getCode());

                    Optional<Place> optionalPlace = placeDTO.getBarangay() == null ? optionalPlaceWithoutBarangay : placeRepository.findPlaceByCodes(
                            placeDTO.getBarangay().getCode(),
                            placeDTO.getMunicipality().getCode(),
                            placeDTO.getProvince().getCode(),
                            placeDTO.getRegionDTO().getCode());

                    if (optionalPlace.isPresent()) {
                        place = optionalPlace.get();
                    } else {
                        place = constructPlace(placeDTO);
                    }

                } else {
                    place = placeRepository.findByDefaultPlace(placeDTO.getDefaultPlace()).orElse(new Place(placeDTO.getDefaultPlace()));
                }
            }

            places.add(place);
        }
        return places;
    }

    public Place constructPlace(PlaceDTO placeDTO) throws Exception {
        Place place = new Place();
        if (placeDTO.getBarangay() == null) {
            place.setBarangay(null);
        } else {
            BarangayDTO barangayDTO = jsonDataLoader.getFromCode(placeDTO.getBarangay().getCode(), AppConstant.BARANGAY_JSON, BarangayDTO.class);
            Barangay barangay = new Barangay(barangayDTO.getCode(), barangayDTO.getName());
            barangay.addPlace(place);
            barangayRepository.save(barangay);

        }
        MunicipalityDTO municipalityDTO = jsonDataLoader.getFromCode(placeDTO.getMunicipality().getCode(), AppConstant.MUNICIPALITY_JSON, MunicipalityDTO.class);
        Municipality municipality = modelMapper.map(municipalityDTO, Municipality.class);
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

        return place;
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
