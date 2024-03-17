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

        for (PlaceDTO placeDTO : travelOrderDTO.getPlaces()) {
            Place place = null;

            if (placeDTO.getDefaultPlace() == null) {
                Optional<Place> optionalPlace = placeRepository.findPlaceByCodes(
                        placeDTO.getBarangay() == null ? null : placeDTO.getBarangay().getCode(),
                        placeDTO.getMunicipality().getCode(),
                        placeDTO.getProvince().getCode(),
                        placeDTO.getRegionDTO().getCode()
                );
                if(optionalPlace.isPresent()){
                    System.out.println(optionalPlace.get());
                    travelOrder.addPlace(optionalPlace.get());
                }else{
                    place = new Place();
                    place.setBuildingName(placeDTO.getBuildingName());

                    Map<String, BarangayDTO> barangayDTOMap = jsonDataLoader.fetchAsMap(AppConstant.BARANGAY_JSON, BarangayDTO.class);

                    if (placeDTO.getBarangay() == null) {
                        place.setBarangay(null);
                    } else {
                        BarangayDTO barangayDTO = barangayDTOMap.get(placeDTO.getBarangay().getCode());
                        Barangay barangay = new Barangay(barangayDTO.getCode(), barangayDTO.getName());
                        barangay.addPlace(place);
                        place.setBarangay(barangay);
                        barangayRepository.save(barangay);

                    }

                    Map<String, MunicipalityDTO> municipalityDTOMap = jsonDataLoader.fetchAsMap(AppConstant.MUNICIPALITY_JSON, MunicipalityDTO.class);
                    MunicipalityDTO municipalityDTO = municipalityDTOMap.get(placeDTO.getMunicipality().getCode());
                    Municipality municipality = new Municipality(municipalityDTO.getCode(), municipalityDTO.getName());
                    municipality.addPlace(place);
                    municipalityRepository.save(municipality);


                    Map<String, ProvinceDTO> provinceDTOMap = jsonDataLoader.fetchAsMap(AppConstant.PROVINCE_JSON, ProvinceDTO.class);
                    ProvinceDTO provinceDTO = provinceDTOMap.get(placeDTO.getProvince().getCode());
                    Province province = new Province(provinceDTO.getCode(), provinceDTO.getName());
                    province.addPlace(place);
                    provinceRepository.save(province);


                    Map<String, RegionDTO> regionDTOMap = jsonDataLoader.fetchAsMap(AppConstant.REGION_JSON, RegionDTO.class);
                    RegionDTO regionDTO = regionDTOMap.get(placeDTO.getRegionDTO().getCode());
                    Region region = new Region(regionDTO.getCode(), regionDTO.getName());
                    region.addPlace(place);
                    regionRepository.save(region);
                    travelOrder.addPlace(place);
                }

            } else {
                place = placeRepository.findByDefaultPlace(placeDTO.getDefaultPlace()).orElse(new Place(placeDTO.getDefaultPlace()));
                travelOrder.addPlace(place);
            }

        }
        for (ReportToDTO reportToDTO : travelOrderDTO.getReportTos()) {
            ReportTo reportTo = reportToRepository.findById(reportToDTO.getId()).orElse(new ReportTo(reportToDTO.getName()));
            reportToRepository.save(reportTo);
            travelOrder.addReportTo(reportTo);
        }

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

        for (PlaceDTO placeDTO : travelOrderDTO.getPlaces()) {
            Place place = null;

            if (placeDTO.getDefaultPlace() == null) {
                Optional<Place> optionalPlace = placeRepository.findPlaceByCodes(
                        placeDTO.getBarangay() == null ? null : placeDTO.getBarangay().getCode(),
                        placeDTO.getMunicipality().getCode(),
                        placeDTO.getProvince().getCode(),
                        placeDTO.getRegionDTO().getCode()
                );
                if(optionalPlace.isPresent()){
                    System.out.println(optionalPlace.get());
                    travelOrder.addPlace(optionalPlace.get());
                }else{
                    place = new Place();
                    place.setBuildingName(placeDTO.getBuildingName());

                    Map<String, BarangayDTO> barangayDTOMap = jsonDataLoader.fetchAsMap(AppConstant.BARANGAY_JSON, BarangayDTO.class);

                    if (placeDTO.getBarangay() == null) {
                        place.setBarangay(null);
                    } else {
                        BarangayDTO barangayDTO = barangayDTOMap.get(placeDTO.getBarangay().getCode());
                        Barangay barangay = new Barangay(barangayDTO.getCode(), barangayDTO.getName());
                        barangay.addPlace(place);
                        place.setBarangay(barangay);
                        barangayRepository.save(barangay);

                    }

                    Map<String, MunicipalityDTO> municipalityDTOMap = jsonDataLoader.fetchAsMap(AppConstant.MUNICIPALITY_JSON, MunicipalityDTO.class);
                    MunicipalityDTO municipalityDTO = municipalityDTOMap.get(placeDTO.getMunicipality().getCode());
                    Municipality municipality = new Municipality(municipalityDTO.getCode(), municipalityDTO.getName());
                    municipality.addPlace(place);
                    municipalityRepository.save(municipality);


                    Map<String, ProvinceDTO> provinceDTOMap = jsonDataLoader.fetchAsMap(AppConstant.PROVINCE_JSON, ProvinceDTO.class);
                    ProvinceDTO provinceDTO = provinceDTOMap.get(placeDTO.getProvince().getCode());
                    Province province = new Province(provinceDTO.getCode(), provinceDTO.getName());
                    province.addPlace(place);
                    provinceRepository.save(province);


                    Map<String, RegionDTO> regionDTOMap = jsonDataLoader.fetchAsMap(AppConstant.REGION_JSON, RegionDTO.class);
                    RegionDTO regionDTO = regionDTOMap.get(placeDTO.getRegionDTO().getCode());
                    Region region = new Region(regionDTO.getCode(), regionDTO.getName());
                    region.addPlace(place);
                    regionRepository.save(region);
                    travelOrder.addPlace(place);
                }

            } else {
                place = placeRepository.findByDefaultPlace(placeDTO.getDefaultPlace()).orElse(new Place(placeDTO.getDefaultPlace()));
                travelOrder.addPlace(place);
            }

        }
        for (ReportToDTO reportToDTO : travelOrderDTO.getReportTos()) {
            ReportTo reportTo = reportToRepository.findById(reportToDTO.getId()).orElse(new ReportTo(reportToDTO.getName()));
            reportToRepository.save(reportTo);
            travelOrder.addReportTo(reportTo);
        }

        travelOrder.setLastTravel(DateTimeConverter.convertToLocalDate(travelOrderDTO.getLastTravel()));

        TravelOrder dbTravelOrder = travelOrderRepository.save(travelOrder);

        return travelOrderMapper.mapToDTO(dbTravelOrder);

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
