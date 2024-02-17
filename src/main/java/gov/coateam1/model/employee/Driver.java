package gov.coateam1.model.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.Position;
import gov.coateam1.model.TripTicket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
@DiscriminatorValue(value = "DRIVER")
public class Driver  extends Employee{


    @OneToMany(mappedBy = "driver",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnore
    private List<TripTicket> tripTickets=new ArrayList<>();

    public Driver(Long id, String name, Position position) {
        super(id, name, position);
    }

    public Driver(String name, Position position) {
        super(name, position);
    }
}
