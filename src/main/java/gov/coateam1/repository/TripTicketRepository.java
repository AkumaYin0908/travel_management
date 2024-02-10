package gov.coateam1.repository;

import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.TripTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripTicketRepository extends JpaRepository<TripTicket,Long> {


    @Query("SELECT tripTicket FROM  TripTicket tripTicket WHERE tripTicket.dateDeparture = :dateDeparture AND tripTicket.dateReturn = :dateReturn")
    Optional<TripTicket> findByDateDepartureAndDateReturn(@Param("dateDeparture") LocalDate dateDeparture, @Param("dateReturn") LocalDate dateReturn);

    @Query("SELECT tripTicket FROM TripTicket tripTicket " +
            "WHERE MONTH(tripTicket.dateDeparture) = :month AND YEAR(tripTicket.dateDeparture) = :year")
    List<TripTicket> findByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);


    @Query("SELECT tripTicket FROM TripTicket tripTicket JOIN FETCH tripTicket.places WHERE tripTicket.id= :id")
    List<TripTicket> findTripTicketAndPlacesById(@Param("id") Long id);

    @Query("SELECT tripTicket FROM TripTicket tripTicket JOIN FETCH tripTicket.places place WHERE place.buildingName = :buildingName")
    List<TripTicket> findTripTicketByBuildingName(@Param("buildingName")String buildingName);


    @Query("SELECT tripTicket FROM TripTicket tripTicket JOIN FETCH tripTicket.places place WHERE place.barangay = :barangay")
    List<TripTicket> findTripTicketByBarangay(@Param("barangay")String barangay);


    @Query("SELECT tripTicket FROM TripTicket tripTicket JOIN FETCH tripTicket.places place WHERE place.municipality = :municipality")
    List<TripTicket> findTripTicketByMunicipality(@Param("municipality")String municipality);

    @Query("SELECT tripTicket FROM TripTicket tripTicket JOIN FETCH tripTicket.places place WHERE place.province = :province")
    List<TripTicket> findTripTicketByProvince(@Param("province")String province);

    @Query("SELECT tripTicket FROM TripTicket tripTicket JOIN FETCH tripTicket.places place WHERE place.id = :id")
    List<TripTicket> findByPlace(@Param("id")Long id);


}
