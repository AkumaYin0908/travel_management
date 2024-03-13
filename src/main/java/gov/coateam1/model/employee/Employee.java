package gov.coateam1.model.employee;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.Position;
import gov.coateam1.model.TravelOrder;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
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


    @OneToMany(mappedBy = "employee",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
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


    public void addTravelOrder(TravelOrder travelOrder){
        if(travelOrders == null){
            travelOrders = new ArrayList<>();
        }

        travelOrders.add(travelOrder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(position, employee.position) && Objects.equals(employeeType, employee.employeeType) && Objects.equals(travelOrders, employee.travelOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position, employeeType, travelOrders);
    }
}
