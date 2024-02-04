package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="dept_head")
public class TeamLeader {



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


    public TeamLeader(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public TeamLeader(String name, String position, boolean active) {
        this.name = name;
        this.position = position;
        this.active = active;
    }


}
