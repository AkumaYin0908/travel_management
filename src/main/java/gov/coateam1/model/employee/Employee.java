package gov.coateam1.model.employee;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.Position;
import gov.coateam1.model.TravelOrder;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EMPLOYEE_TYPE",discriminatorType = DiscriminatorType.STRING)
@ToString(onlyExplicitlyIncluded = true)
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="position")
    private Position position;


    @Setter(AccessLevel.NONE)
    @Column(name = "EMPLOYEE_TYPE",insertable=false, updatable=false)
    private String employeeType;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<TravelOrder> travelOrders;

    public Employee(Long id, String name, Position position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public Employee(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public Employee(Long id, String name, Position position, List<TravelOrder> travelOrders) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.travelOrders = travelOrders;
    }

    public void addTravelOrder(TravelOrder travelOrder){
        if(travelOrders == null){
            travelOrders = new ArrayList<>();
        }

        travelOrders.add(travelOrder);
        travelOrder.setEmployee(this);
    }

}
