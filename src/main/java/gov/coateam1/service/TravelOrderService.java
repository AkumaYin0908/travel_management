package gov.coateam1.service;

import gov.coateam1.model.TravelOrder;
import gov.coateam1.payload.TravelOrderDTO;

import java.time.LocalDate;
import java.util.List;

public interface TravelOrderService {


    List<TravelOrderDTO> findAll();

  //  TravelOrderDTO findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn);

    TravelOrderDTO findById(Long id);

    TravelOrderDTO add(Long employeeId, TravelOrderDTO travelOrderDTO) throws Exception;

    TravelOrderDTO update(TravelOrderDTO travelOrderDTO, Long id, Long employeeId) throws Exception;

//    List<TravelOrderDTO> findTravelOrderAndReportTosById(Long id);
//
//    List<TravelOrderDTO> findTravelOrderAndPlacesById(Long id);
//
//    List<TravelOrderDTO> findByBuildingName(String buildingName);
//
//    List<TravelOrderDTO> findByBarangay(String barangay);
//
//    List<TravelOrderDTO> findByMunicipality(String municipality);
//
//    List<TravelOrderDTO> findByProvince(String province);
//
//
//    LocalDate findByNameOrderByDateReturnDESC(String name,LocalDate dateReturn);

    void delete(Long id);


}
