package gov.coateam1.service;

import gov.coateam1.model.TravelOrder;

import java.time.LocalDate;
import java.util.List;

public interface TravelOrderService {


    List<TravelOrder> findAll();
    TravelOrder findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn);

    TravelOrder add(TravelOrder travelOrder);

    TravelOrder update(TravelOrder travelOrder);

    List<TravelOrder> findTravelOrderAndReportTosById(Long id);

    List<TravelOrder> findTravelOrderAndPlacesById(Long id);

    List<TravelOrder> findByBuildingName(String buildingName);

    List<TravelOrder> findByBarangay(String barangay);

    List<TravelOrder> findByMunicipality(String municipality);

    List<TravelOrder> findByProvince(String province);


    LocalDate findByNameOrderByDateReturnDESC(String name,LocalDate dateReturn);



    void delete(Long id);


}
