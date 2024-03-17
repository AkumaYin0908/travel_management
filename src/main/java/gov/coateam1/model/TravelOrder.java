package gov.coateam1.model;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.place.Place;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "travel_order")
public class TravelOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "date_issued")
    private LocalDate dateIssued;

    @Column(name = "date_departure")

    private LocalDate dateDeparture;

    @Column(name = "date_return")
    private LocalDate dateReturn;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose")
    private Purpose purpose;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;



    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "reportto_travelorder",
            joinColumns = @JoinColumn(name = "travelorder_id"),
            inverseJoinColumns = @JoinColumn(name = "reportto_id"))
    private Set<ReportTo> reportTos;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "place_travelorder",
            joinColumns = @JoinColumn(name = "travelorder_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    private Set<Place> places;


    @Column(name = "last_travel")
    private LocalDate lastTravel;

    public TravelOrder(Long id, LocalDate dateIssued, LocalDate dateDeparture, LocalDate dateReturn, Purpose purpose, Vehicle vehicle, Set<ReportTo> reportTos, Set<Place> places, LocalDate lastTravel) {
        this.id = id;
        this.dateIssued = dateIssued;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.purpose = purpose;
        this.vehicle = vehicle;
        this.reportTos = reportTos;
        this.places = places;
        this.lastTravel = lastTravel;
    }

    public void addPlace(Place place) {
        if (places == null) {
            places = new LinkedHashSet<>();
        }
        places.add(place);
        if(place.getTravelOrders() == null){
            place.setTravelOrders(new ArrayList<>());
        }
        place.getTravelOrders().add(this);
    }

    public void removePlace(Place place){
        places.remove(place);
        place.getTravelOrders().remove(this);
    }

    public void addReportTo(ReportTo reportTo) {
        if (reportTos == null) {
            reportTos = new LinkedHashSet<>();
        }
        reportTos.add(reportTo);

        if(reportTo.getTravelOrders() == null){
            reportTo.setTravelOrders(new ArrayList<>());
        }
        reportTo.getTravelOrders().add(this);

    }


    public void removeReportTo(ReportTo reportTo){
        reportTos.remove(reportTo);
        reportTo.getTravelOrders().remove(this);
    }
}
