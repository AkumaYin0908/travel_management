package gov.coateam1.service;

import gov.coateam1.model.TripTicket;

import java.time.LocalDate;
import java.util.List;

public interface TripTicketService {


    List<TripTicket> findAll();

    List<TripTicket> findByMonthAndYear(Integer month, Integer year);

    TripTicket findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn);


    List<TripTicket> findTripTicketAndPlacesById(Long id);

    List<TripTicket> findByPlace(Long id);

    List<TripTicket> findByBuildingName(String buildingName);

    List<TripTicket> findByDefaultPlace(String defaultPlace);

    List<TripTicket> findByBarangay(String barangay);

    List<TripTicket> findByMunicipality(String municipality);

    List<TripTicket> findByProvince(String province);

    TripTicket add(TripTicket tripTicket);

    TripTicket update(TripTicket tripTicket);

    void delete(Long id);
}
