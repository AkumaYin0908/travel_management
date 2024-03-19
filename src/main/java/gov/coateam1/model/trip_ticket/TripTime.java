package gov.coateam1.model.trip_ticket;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trip_time")
public class TripTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="time_office_departure")
    private LocalTime timeOfficeDeparture;

    @Column(name="time_place_arrival")
    private LocalTime timePlaceArrival;

    @Column(name="time_place_departure")
    private LocalTime timePlaceDeparture;

    @Column(name="time_office_arrival")
    private LocalTime timeOfficeArrival;
}
