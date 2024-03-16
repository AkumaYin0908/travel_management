package gov.coateam1.model.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.Position;
import gov.coateam1.model.TripTicket;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@Entity
@Getter
@Setter
@DiscriminatorValue(value = "DRIVER")
public class Driver  extends Employee{


    @ToString.Exclude
    @OneToMany(mappedBy = "driver",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<TripTicket> tripTickets;

    public Driver(Long id, String name, Position position) {
        super(id, name, position);
    }

    public Driver(String name, Position position) {
        super(name, position);
    }
}
