package gov.coateam1.model.employee;

import gov.coateam1.model.Position;
import gov.coateam1.model.trip_ticket.TripTicket;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@Entity
@Getter
@Setter
@DiscriminatorValue(value = "DRIVER")
public class Driver  extends Employee{


    @ToString.Exclude
    @OneToMany(mappedBy = "driver",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Set<TripTicket> tripTickets;

    public Driver(Long id, String name, Position position) {
        super(id, name, position);
    }

    public Driver(String name, Position position) {
        super(name, position);
    }

}
