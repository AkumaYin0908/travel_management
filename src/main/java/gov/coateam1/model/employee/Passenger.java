package gov.coateam1.model.employee;

import gov.coateam1.model.Position;
import gov.coateam1.model.trip_ticket.TripTicket;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@DiscriminatorValue(value = "PASSENGER")
@NoArgsConstructor
public class Passenger extends Employee {


    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="travel_passengers",
            joinColumns = @JoinColumn(name="employee_id"),
            inverseJoinColumns = @JoinColumn(name="tripticket_id"))

    private Set<TripTicket> tripTickets;


    public Passenger(Long id, String name, Position position) {
        super(id, name, position);
    }

    public Passenger(String name, Position position) {
        super(name, position);
    }
}
