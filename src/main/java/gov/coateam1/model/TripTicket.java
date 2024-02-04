package gov.coateam1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.place.Place;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @JoinColumn(name="employee")
    private Employee employee;

    @Column(name="date")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate date;

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
    private List<Employee> passengers;


    @Column(name="time_office_departure")
    @JsonFormat(pattern = "h:mm a")
    private LocalTime timeOfficeDeparture;

    @Column(name="time_place_arrival")
    @JsonFormat(pattern = "h:mm a")
    private LocalTime timePlaceArrival;

    @Column(name="time_place_departure")
    @JsonFormat(pattern = "h:mm a")
    private LocalTime timePlaceDeparture;

    @Column(name="time_office_arrival")
    @JsonFormat(pattern = "h:mm a")
    private LocalTime timeOfficeArrival;

    @Column(name="fuel_balance")
    private BigDecimal fuelBalance;

    @Column(name="issued_fuel")
    private BigDecimal issuedFuel;

    @Column(name="purchased_Fuel")
    private BigDecimal purchasedFuel;

    @Column(name="consumed_fuel")
    private BigDecimal consumedFuel;

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





}
