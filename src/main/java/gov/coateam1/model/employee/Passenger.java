package gov.coateam1.model.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.Position;
import gov.coateam1.model.TripTicket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue(value = "PASSENGER")
@NoArgsConstructor
public class Passenger extends Employee {


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="travel_passengers",
            joinColumns = @JoinColumn(name="employee_id"),
            inverseJoinColumns = @JoinColumn(name="tripticket_id"))
    @JsonIgnore
    private List<TripTicket> tripTickets;


    public Passenger(Long id, String name, Position position) {
        super(id, name, position);
    }

    public Passenger(String name, Position position) {
        super(name, position);
    }
}
