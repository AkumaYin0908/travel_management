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

}
