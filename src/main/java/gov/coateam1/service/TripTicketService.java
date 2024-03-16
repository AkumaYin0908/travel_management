package gov.coateam1.service;

import gov.coateam1.model.TripTicket;
import gov.coateam1.payload.TripTicketDTO;

import java.time.LocalDate;
import java.util.List;

public interface TripTicketService {


    List<TripTicketDTO> findAll();

    List<TripTicketDTO> findByMonthAndYear(Integer month, Integer year);

    TripTicketDTO findByDateDepartureAndDateReturn(String dateDeparture, String dateReturn);

    TripTicketDTO findById(Long id);


    List<TripTicketDTO> findTripTicketAndPlacesById(Long id);

    List<TripTicketDTO> findByPlace(Long id);

    List<TripTicketDTO> findByBuildingName(String buildingName);

    List<TripTicketDTO> findByDefaultPlace(String defaultPlace);

//    List<TripTicketDTO> findByBarangay(String barangay);
//
//    List<TripTicketDTO> findByMunicipality(String municipality);
//
//    List<TripTicketDTO> findByProvince(String province);

    TripTicketDTO add(TripTicketDTO tripTicketDTO);

    TripTicketDTO update(TripTicketDTO tripTicketDTO, Long id);

    void delete(Long id);
}
