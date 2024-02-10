package gov.coateam1.repository;

import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TravelOrderRepository extends JpaRepository<TravelOrder,Long> {

    @Query("SELECT travelOrder FROM TravelOrder travelOrder JOIN FETCH travelOrder.reportTos where travelOrder.id = :id")
    List <TravelOrder> findTravelOrderAndReportTosById(@Param("id")Long id);

    @Query("SELECT travelOrder FROM TravelOrder travelOrder WHERE travelOrder.employee.name = :name")
    List<TravelOrder> findByDriverName(@Param("name") String name);

    @Query("SELECT travelOrder FROM TravelOrder travelOrder WHERE MONTH(travelOrder.dateDeparture) = :month")
    List<TravelOrder> findByMonthDateDeparture(@Param("month") Integer month);

    @Query("SELECT travelOrder FROM TravelOrder travelOrder JOIN FETCH travelOrder.places  WHERE travelOrder.id = :id")
    List<TravelOrder> findTravelOrderAndPlacesById(@Param("id")Long id);

    @Query("SELECT travelOrder FROM TravelOrder travelOrder WHERE travelOrder.dateDeparture = :dateDeparture AND travelOrder.dateReturn = :dateReturn")
    Optional<TravelOrder> findByDateDepartureAndDateReturn(@Param("dateDeparture")LocalDate dateDeparture, @Param("dateReturn") LocalDate dateReturn);

    @Query("SELECT travelOrder.dateReturn FROM TravelOrder travelOrder WHERE travelOrder.employee.name =:name ORDER BY travelOrder.dateReturn DESC")
    Optional<LocalDate> findByNameOrderByDateReturnDESC(@Param("name") String name);

}
