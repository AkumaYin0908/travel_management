package gov.coateam1.service.impl;

import gov.coateam1.exception.TripTicketNotFoundException;
import gov.coateam1.model.Distance;
import gov.coateam1.model.FuelBalance;
import gov.coateam1.model.TripTicket;
import gov.coateam1.repository.DistanceRepository;
import gov.coateam1.repository.FuelBalanceRepository;
import gov.coateam1.repository.TripTicketRepository;
import gov.coateam1.service.DistanceService;
import gov.coateam1.service.FuelBalanceService;
import gov.coateam1.service.TripTicketService;
import gov.coateam1.service.place.PlaceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TripTicketServiceImpl implements TripTicketService {

    private final TripTicketRepository tripTicketRepository;
    private final FuelBalanceService fuelBalanceService;
    private final DistanceService distanceService;
    private final PlaceService placeService;


    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
    @Override
    public List<TripTicket> findAll() {
        return tripTicketRepository.findAll();
    }

    @Override
    public List<TripTicket> findByMonthAndYear(Integer month, Integer year) {
        return tripTicketRepository.findByMonthAndYear(month,year);
    }

    @Override
    public TripTicket findByDateDepartureAndDateReturn(LocalDate dateDeparture, LocalDate dateReturn) {
        return tripTicketRepository.findByDateDepartureAndDateReturn(dateDeparture,dateReturn)
                .orElseThrow(() -> {

                    String message = (dateDeparture.isEqual(dateReturn)) ? String.format("No Trip Ticket found with this following date: %s",dateTimeFormatter.format(dateDeparture))
                            : String.format("No Trip Ticket found with these following dates: departure: %s , return : %s",dateTimeFormatter.format(dateDeparture), dateTimeFormatter.format(dateReturn));

                    return new TripTicketNotFoundException(message);
                });
    }

    @Override
    public List<TripTicket> findTripTicketAndPlacesById(Long id) {
        return tripTicketRepository.findTripTicketAndPlacesById(id);
    }

    @Override
    public List<TripTicket> findByPlace(Long id) {
        return tripTicketRepository.findByPlace(id);
    }

    @Override
    public List<TripTicket> findByBuildingName(String buildingName) {
        return tripTicketRepository.findTripTicketAndPlacesByBuildingName(buildingName);
    }

    @Override
    public List<TripTicket> findByDefaultPlace(String defaultPlace) {
        return tripTicketRepository.findTripTicketAndPlacesByDefaultPlace(defaultPlace);
    }

    @Override
    public List<TripTicket> findByBarangay(String barangay) {
        return tripTicketRepository.findTripTicketAndPlacesByBarangayName(barangay);
    }

    @Override
    public List<TripTicket> findByMunicipality(String municipality) {
        return tripTicketRepository.findTripTicketAndPlacesByMunicipalityName(municipality);
    }

    @Override
    public List<TripTicket> findByProvince(String province) {
        return tripTicketRepository.findTripTicketAndPlacesByProvinceName(province);
    }

    @Override
    @Transactional
    public TripTicket add(TripTicket tripTicket) {
        FuelBalance fuelBalance = fuelBalanceService.findFuelBalance();
        tripTicket.setFuelBalance(fuelBalance.getFuel());

        Distance distance = distanceService.findDistance();
        tripTicket.setStartDistance(distance.getDistance());


        return tripTicketRepository.save(tripTicket) ;
    }

    @Override
    @Transactional
    public TripTicket update(TripTicket tripTicket) {
        fuelBalanceService.update(tripTicket.getRemainingFuel());
        distanceService.update(tripTicket.getEndDistance());

        return tripTicketRepository.save(tripTicket);
    }

    @Override
    public void delete(Long id) {
        tripTicketRepository.deleteById(id);
    }
}
