package gov.coateam1.service.impl;


import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.model.TravelOrder;
import gov.coateam1.payload.TravelOrderDTO;
import gov.coateam1.repository.TravelOrderRepository;
import gov.coateam1.service.TravelOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TravelOrderServiceImpl implements TravelOrderService {

    private final TravelOrderRepository travelOrderRepository;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    @Override
    public List<TravelOrderDTO> findAll() {
        return travelOrderRepository.findAll();
    }

    @Override
    public TravelOrderDTO findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn) {
        return travelOrderRepository.findByDateDepartureAndDateReturn(dateDeparture,dateReturn)
                .orElseThrow(() -> {
                        String message = (dateDeparture.isEqual(dateReturn)) ? String.format("No Travel Order found with this following date: %s",dateTimeFormatter.format(dateDeparture))
                                : String.format("No Travel Order found with these following dates: departure: %s , return : %s",dateTimeFormatter.format(dateDeparture), dateTimeFormatter.format(dateReturn));

                       return  new ResourceNotFoundException(message);
                });
    }

    @Override
    public TravelOrderDTO findById(Long id) {
        return travelOrderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TravelOrder","id",id));
    }

    @Override
    public TravelOrderDTO add(TravelOrderDTO travelOrderDTO) {
        return travelOrderRepository.save(travelOrder);
    }

    @Override
    public TravelOrderDTO update(TravelOrderDTO travelOrderDTO ) {
        return travelOrderRepository.save(travelOrder);
    }

    @Override
    public List<TravelOrderDTO> findTravelOrderAndReportTosById(Long id) {
        return travelOrderRepository.findTravelOrderAndReportTosById(id);
    }

    @Override
    public List<TravelOrderDTO> findTravelOrderAndPlacesById(Long id) {
        return travelOrderRepository.findTravelOrderAndPlacesById(id);
    }

    @Override
    public List<TravelOrderDTO> findByBuildingName(String buildingName) {
        return travelOrderRepository.findTravelOrderAndPlacesByBuildingName(buildingName);
    }

    @Override
    public List<TravelOrderDTO> findByBarangay(String barangay) {
        return travelOrderRepository.findTravelOrderAndPlacesByBarangayName(barangay);
    }

    @Override
    public List<TravelOrderDTO> findByMunicipality(String municipality) {
        return travelOrderRepository.findTravelOrderAndPlacesByMunicipalityName(municipality);
    }

    @Override
    public List<TravelOrderDTO> findByProvince(String province) {
        return travelOrderRepository.findTravelOrderAndPlacesByProvinceName(province);
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
