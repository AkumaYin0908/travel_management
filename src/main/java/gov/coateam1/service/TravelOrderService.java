package gov.coateam1.service;

import gov.coateam1.model.TravelOrder;

import java.time.LocalDate;

public interface TravelOrderService {


    TravelOrder findTravelOrderByDateDeparture(LocalDate dateDeparture);

    TravelOrder addTravelOrder(TravelOrder travelOrder);

    TravelOrder updateTravelOrder(TravelOrder travelOrder);

    TravelOrder findTravelOrderAndReportTosById(Long id);

    TravelOrder findTravelOrderAndPlacesById(Long id);

    void deleteTravelOrder(Long id);


}
