package gov.coateam1.service.impl;


import gov.coateam1.exception.ResourceNotFoundException;
import gov.coateam1.mapper.TravelOrderMapper;
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
    private final TravelOrderMapper travelOrderMapper;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    @Override
    public List<TravelOrderDTO> findAll() {
        return travelOrderRepository.findAll().stream().map(travelOrderMapper::mapToDTO).toList();
    }

    @Override
    public TravelOrderDTO findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn) {
        TravelOrder travelOrder = travelOrderRepository.findByDateDepartureAndDateReturn(dateDeparture,dateReturn)
                .orElseThrow(() -> {
                        String message = (dateDeparture.isEqual(dateReturn)) ? String.format("No Travel Order found with this following date: %s",dateTimeFormatter.format(dateDeparture))
                                : String.format("No Travel Order found with these following dates: departure: %s , return : %s",dateTimeFormatter.format(dateDeparture), dateTimeFormatter.format(dateReturn));

                       return  new ResourceNotFoundException(message);
                });


        return travelOrderMapper.mapToDTO(travelOrder);
    }

    @Override
    public TravelOrderDTO findById(Long id) {
        TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TravelOrder","id",id));
        return travelOrderMapper.mapToDTO(travelOrder);
    }

    @Override
    public TravelOrderDTO add(TravelOrderDTO travelOrderDTO) throws Exception {

        TravelOrder travelOrder = travelOrderMapper.mapToModel(travelOrderDTO);
        TravelOrder dbTravelOrder = travelOrderRepository.save(travelOrder);
        travelOrderDTO.setId(dbTravelOrder.getId());
         return travelOrderDTO;
    }

    @Override
    public TravelOrderDTO update(TravelOrderDTO travelOrderDTO, Long id ) throws Exception {
       TravelOrder travelOrder = travelOrderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("TravelOrder","id",id));
       travelOrderMapper.mapToModel(travelOrderDTO,travelOrder);
       travelOrderRepository.save(travelOrder);
       travelOrderDTO.setId(id);
       return travelOrderDTO;

    }

    @Override
    public List<TravelOrderDTO> findTravelOrderAndReportTosById(Long id) {
        return travelOrderRepository.findTravelOrderAndReportTosById(id).stream().map(travelOrderMapper::mapToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findTravelOrderAndPlacesById(Long id) {
        return travelOrderRepository.findTravelOrderAndPlacesById(id).stream().map(travelOrderMapper::mapToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findByBuildingName(String buildingName) {
        return travelOrderRepository.findTravelOrderAndPlacesByBuildingName(buildingName).stream().map(travelOrderMapper::mapToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findByBarangay(String barangay) {
        return travelOrderRepository.findTravelOrderAndPlacesByBarangayName(barangay).stream().map(travelOrderMapper::mapToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findByMunicipality(String municipality) {
        return travelOrderRepository.findTravelOrderAndPlacesByMunicipalityName(municipality).stream().map(travelOrderMapper::mapToDTO).toList();
    }

    @Override
    public List<TravelOrderDTO> findByProvince(String province) {
        return travelOrderRepository.findTravelOrderAndPlacesByProvinceName(province).stream().map(travelOrderMapper::mapToDTO).toList();
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
