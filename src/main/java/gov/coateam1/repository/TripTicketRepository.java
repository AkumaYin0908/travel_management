package gov.coateam1.repository;

import gov.coateam1.model.trip_ticket.TripTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripTicketRepository extends JpaRepository<TripTicket,Long> {

}
