package gov.coateam1.repository;

import gov.coateam1.constants.TravelOrderQueryConstant;
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


    @Query("SELECT travelOrder FROM TravelOrder travelOrder JOIN FETCH travelOrder.places place WHERE place.buildingName = :buildingName")
    List<TravelOrder> findTravelOrderAndPlacesByBuildingName(@Param("buildingName")String buildingName);

    @Query(value = TravelOrderQueryConstant.FIND_BY_DATE_ISSUED,nativeQuery = true)
    List<TravelOrder> findByDateIssued(LocalDate dateIssued);

}
