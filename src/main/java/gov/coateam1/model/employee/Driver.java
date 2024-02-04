package gov.coateam1.model.employee;

import gov.coateam1.model.Position;
import gov.coateam1.model.TripTicket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue(value = "DRIVER")
public class Driver  extends Employee{



    @OneToMany(mappedBy = "employee",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<TripTicket> tripTickets;

    public Driver(Long id, String name, Position position) {
        super(id, name, position);
    }

    public Driver(String name, Position position) {
        super(name, position);
    }
}
