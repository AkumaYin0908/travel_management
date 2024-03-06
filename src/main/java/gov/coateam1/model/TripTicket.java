package gov.coateam1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.employee.Passenger;
import gov.coateam1.model.place.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name="trip_ticket")
public class TripTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="driver")
    private Driver driver;

    @Column(name="date_departure")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateDeparture;

    @Column(name="date_return")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateReturn;



    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="place_tripticket",
    joinColumns = @JoinColumn(name="tripticket_id"),
    inverseJoinColumns = @JoinColumn(name="place_id"))
    private List<Place> places;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="travel_passengers",
            joinColumns = @JoinColumn(name="tripticket_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id"))
    private List<Passenger> passengers;


    @Column(name="time_office_departure")
    private LocalTime timeOfficeDeparture;

    @Column(name="time_place_arrival")
    private LocalTime timePlaceArrival;

    @Column(name="time_place_departure")
    private LocalTime timePlaceDeparture;

    @Column(name="time_office_arrival")
    private LocalTime timeOfficeArrival;

    @Column(name="fuel_balance")
    private BigDecimal fuelBalance;

    @Column(name="issued_fuel")
    private BigDecimal issuedFuel;

    @Column(name="purchased_Fuel")
    private BigDecimal purchasedFuel;

    @Getter(AccessLevel.NONE)
    @Column(name="consumed_fuel")
    private BigDecimal consumedFuel;

    @Getter(AccessLevel.NONE)
    @Column(name="remaining_fuel")
    private BigDecimal remainingFuel;

    @Column(name="gear_oil")
    private BigDecimal gearOil;

    @Column(name="lubricant_oil")
    private BigDecimal lubricantOil;

    @Column(name="start_distance")
    private Long startDistance;

    @Column(name="end_distance")
    private Long endDistance;

    @Getter(AccessLevel.NONE)
    @Column(name="distance_travelled")
    private Integer distanceTravelled;

    @Column(name="remarks")
    private String remarks;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="purpose")
    private Purpose purpose;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="vehicle")
    private Vehicle vehicle;


    public Integer getDistanceTravelled() {
        return Math.toIntExact(this.endDistance - this.startDistance);
    }

    public BigDecimal getConsumedFuel() {
        BigDecimal divisor = new BigDecimal(7);
        return  new BigDecimal(this.getDistanceTravelled()).divide(divisor,2,RoundingMode.DOWN);
    }

    public BigDecimal getRemainingFuel() {
        BigDecimal consumedFuel = this.getConsumedFuel();
        BigDecimal fuelBalance=this.fuelBalance;
        BigDecimal issuedFuel = this.issuedFuel;
        return fuelBalance.add(issuedFuel).subtract(consumedFuel);
    }
}
