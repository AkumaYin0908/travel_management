package gov.coateam1.model.employee;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.Position;
import gov.coateam1.model.TravelOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.ArrayList;
import java.util.List;



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

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="position")
    private Position position;


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
}
