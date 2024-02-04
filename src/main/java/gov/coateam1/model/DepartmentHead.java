package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="dept_head")
public class DepartmentHead {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="position")
    private String position;

    @Column(name="active")
    private boolean active;


    public DepartmentHead(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public DepartmentHead(String name, String position, boolean active) {
        this.name = name;
        this.position = position;
        this.active = active;
    }


}
