package gov.coateam1.model.trip_ticket;

import gov.coateam1.model.Purpose;
import gov.coateam1.model.Vehicle;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.Place;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trip_ticket")
public class TripTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="driver")
    private Driver driver;

    @Column(name="date_departure")
    private LocalDate dateDeparture;

    @Column(name="date_return")
    private LocalDate dateReturn;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="place_tripticket",
    joinColumns = @JoinColumn(name="tripticket_id"),
    inverseJoinColumns = @JoinColumn(name="place_id"))
    private Set<Place> places;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="travel_passengers",
            joinColumns = @JoinColumn(name="tripticket_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id"))
    private Set<Passenger> passengers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="trip_time")
    private TripTime tripTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_fuel")
    private TripFuel tripFuel;

    @Column(name="gear_oil")
    private BigDecimal gearOil;

    @Column(name="lubricant_oil")
    private BigDecimal lubricantOil;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="trip_distance")
    private TripDistance tripDistance;

    @Column(name="remarks")
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="purpose")
    private Purpose purpose;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle")
    private Vehicle vehicle;



    public void addPlace(Place place) {
        if (places == null) {
            places = new LinkedHashSet<>();
        }
        places.add(place);
        if(place.getTripTickets() == null){
            place.setTripTickets(new LinkedHashSet<>());
        }
        place.getTripTickets().add(this);
    }

    public void removePlace(Place place){
        places.remove(place);
        place.getTripTickets().remove(this);
    }

    public void addPassenger(Passenger passenger){
        if(passengers == null){
            passengers = new LinkedHashSet<>();
        }

        passengers.add(passenger);
        if(passenger.getTripTickets() == null){
            passenger.setTripTickets(new LinkedHashSet<>());
        }
    }

    public void removePassenger(Passenger passenger){
        this.getPassengers().remove(passenger);
        passenger.getTripTickets().remove(this);
    }

}
