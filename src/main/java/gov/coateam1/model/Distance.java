package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="distance")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="distance")
    private Long distance;

    public Distance(Long distance) {
        this.distance = distance;
    }
}
