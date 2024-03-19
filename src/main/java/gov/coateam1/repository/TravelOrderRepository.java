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


    @Query(value = TravelOrderQueryConstant.FIND_BY_DATE_ISSUED,nativeQuery = true)
    List<TravelOrder> findByDateIssued(LocalDate dateIssued);

}
