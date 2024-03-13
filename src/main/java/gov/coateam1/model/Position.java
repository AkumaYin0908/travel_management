package gov.coateam1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.coateam1.model.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="position")
public class Position {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "position",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},orphanRemoval = true)
    private List<Employee> employees;

    public Position(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Position(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee){
        if(employees == null){
            employees=new ArrayList<>();
        }

        employees.add(employee);
        employee.setPosition(this);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
        employee.setPosition(null);
    }
}
