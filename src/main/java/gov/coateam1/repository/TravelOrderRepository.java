package gov.coateam1.repository;

import gov.coateam1.model.ReportTo;
import gov.coateam1.model.TravelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TravelOrderRepository extends JpaRepository<TravelOrder,Long> {

    @Query("SELECT travelOrder FROM TravelOrder travelOrder JOIN FETCH travelOrder.reportTos WHERE travelOrder.id= :id")
    TravelOrder findTravelOrderAndReportTosById(@Param("id")Long id);

    @Query("SELECT travelOrder FROM TravelOrder travelOrder JOIN FETCH travelOrder.places WHERE travelOrder.id = :id")
    TravelOrder findTravelOrderAndPlacesById(@Param("id")Long id);

    @Query("SELECT travelOrder FROM TravelOrder travelOrder WHERE travelOrder.dateDeparture = : dateDeparture")
    TravelOrder findTravelOrderByDateDeparture(@Param("dateDeparture")LocalDate dateDeparture);

}
