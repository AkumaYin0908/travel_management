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

    @JsonIgnore
    @OneToMany(mappedBy = "position",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Employee> employees;

    public Position(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Position(String name) {
        this.name = name;
    }
}
