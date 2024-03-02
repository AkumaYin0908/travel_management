package gov.coateam1.service.impl;


import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.TravelOrderMapper;
import gov.coateam1.model.Purpose;
import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.place.Place;
import gov.coateam1.payload.PurposeDTO;
import gov.coateam1.payload.TravelOrderDTO;
import gov.coateam1.payload.employee.EmployeeDTO;
import gov.coateam1.repository.TravelOrderRepository;
import gov.coateam1.service.TravelOrderService;
import gov.coateam1.util.DateTimeConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TravelOrderServiceImpl implements TravelOrderService {

    private final TravelOrderRepository travelOrderRepository;
    private final ModelMapper modelMapper;
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
    private final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    private TravelOrderDTO convertToDTO(TravelOrder travelOrder) {

        Converter<LocalDate, String> toString =
                ctx -> ctx.getSource() == null ? null : dateFormatter.format(ctx.getSource());

        modelMapper.typeMap(TravelOrder.class, TravelOrderDTO.class)
                .addMappings(mapping -> mapping.using(toString).map(TravelOrder::getDateIssued, TravelOrderDTO::setDateIssued))
                .addMappings(mapping -> mapping.using(toString).map(TravelOrder::getDateDeparture, TravelOrderDTO::setDateDeparture))
                .addMappings(mapping -> mapping.using(toString).map(TravelOrder::getDateReturn, TravelOrderDTO::setDateReturn))
                .addMappings(mapping -> mapping.using(toString).map(TravelOrder::getLastTravel, TravelOrderDTO::setLastTravel));

        return modelMapper.map(travelOrder, TravelOrderDTO.class);

    }

    private TravelOrder convertToModel(TravelOrderDTO travelOrderDTO) {
        Converter<String, LocalDate> toLocalDate =
                ctx -> ctx.getSource() == null ? null : LocalDate.parse(ctx.getSource(), dateFormatter);

        modelMapper.typeMap(TravelOrderDTO.class, TravelOrder.class)
                .addMappings(mapping -> mapping.using(toLocalDate).map(TravelOrderDTO::getDateIssued, TravelOrder::setDateIssued))
                .addMappings(mapping -> mapping.using(toLocalDate).map(TravelOrderDTO::getDateDeparture, TravelOrder::setDateDeparture))
                .addMappings(mapping -> mapping.using(toLocalDate).map(TravelOrderDTO::getDateReturn, TravelOrder::setDateReturn))
                .addMappings(mapping -> mapping.using(toLocalDate).map(TravelOrderDTO::getLastTravel, TravelOrder::setLastTravel));


        return modelMapper.map(travelOrderDTO, TravelOrder.class);

    }

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    @Override
    public List<TravelOrderDTO> findAll() {
        return travelOrderRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    public TravelOrderDTO findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn) {
        TravelOrder travelOrder = travelOrderRepository.findByDateDepartureAndDateReturn(dateDeparture, dateReturn)
                .orElseThrow(() -> {
                    String message = (dateDeparture.isEqual(dateReturn)) ? String.format("No Travel Order found with this following date: %s", dateTimeFormatter.format(dateDeparture))
                            : String.format("No Travel Order found with these following dates: departure: %s , return : %s", dateTimeFormatter.format(dateDeparture), dateTimeFormatter.format(dateReturn));

                    return new ResourceNotFoundException(message);
                });


        return modelMapper.map(travelOrder, TravelOrderDTO.class);
    }

    @Override
    public TravelOrderDTO findById(Long id) {
        TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TravelOrder", "id", id));
        return this.convertToDTO(travelOrder);
    }

    @Override
    public TravelOrderDTO add(TravelOrderDTO travelOrderDTO) {

        TravelOrder travelOrder = this.convertToModel(travelOrderDTO);
        TravelOrder dbTravelOrder = travelOrderRepository.save(travelOrder);
        travelOrderDTO.setId(dbTravelOrder.getId());
        return travelOrderDTO;
    }

    @Override
    public TravelOrderDTO update(TravelOrderDTO travelOrderDTO, Long id) {
        TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TravelOrder", "id", id));
        travelOrder.setEmployee(modelMapper.map(travelOrderDTO.getEmployeeDTO(), Driver.class));
        travelOrder.setDateIssued(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateIssued()));
        travelOrder.setDateDeparture(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateDeparture()));
        travelOrder.setDateReturn(DateTimeConverter.convertToLocalDate(travelOrderDTO.getDateReturn()));
        travelOrder.setLastTravel(DateTimeConverter.convertToLocalDate(travelOrderDTO.getLastTravel()));
        travelOrder.setVehicle(modelMapper.map(travelOrder.getVehicle(), Vehicle.class));
        travelOrder.setPurpose(modelMapper.map(travelOrder.getPurpose(), Purpose.class));
        travelOrder.setReportTos(travelOrder.getReportTos().stream().map(r -> modelMapper.map(r, ReportTo.class)).toList());
        travelOrderRepository.save(travelOrder);
        travelOrderDTO.setId(id);
        return travelOrderDTO;

    }

    @Override
    public List<TravelOrderDTO> findTravelOrderAndReportTosById(Long id) {
        return travelOrderRepository.findTravelOrderAndReportTosById(id).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findTravelOrderAndPlacesById(Long id) {
        return travelOrderRepository.findTravelOrderAndPlacesById(id).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findByBuildingName(String buildingName) {
        return travelOrderRepository.findTravelOrderAndPlacesByBuildingName(buildingName).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findByBarangay(String barangay) {
        return travelOrderRepository.findTravelOrderAndPlacesByBarangayName(barangay).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findByMunicipality(String municipality) {
        return travelOrderRepository.findTravelOrderAndPlacesByMunicipalityName(municipality).stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findByProvince(String province) {
        return travelOrderRepository.findTravelOrderAndPlacesByProvinceName(province).stream().map(this::convertToDTO).toList();
    }

    @Override
    public LocalDate findByNameOrderByDateReturnDESC(String name, LocalDate dateReturn) {
        return travelOrderRepository.findByNameOrderByDateReturnDESC(name).orElse(dateReturn);
    }

    @Override
    public void delete(Long id) {
        travelOrderRepository.deleteById(id);
    }
}
