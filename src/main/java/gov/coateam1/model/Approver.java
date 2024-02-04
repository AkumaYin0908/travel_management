package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="approver")
public class Approver {

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


    public Approver(String name, String position, boolean active) {
        this.name = name;
        this.position = position;
        this.active = active;
    }

    public Approver(String name, String position) {
        this.name = name;
        this.position = position;
    }


}
