package gov.coateam1.model.trip_ticket;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trip_distance")
public class TripDistance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="start_distance")
    private Long startDistance;

    @Column(name="end_distance")
    private Long endDistance;

    @Column(name="distance_travelled")
    private Integer distanceTravelled;
}
