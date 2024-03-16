package gov.coateam1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.coateam1.model.employee.Driver;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.place.Place;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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



    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "reportto_travelorder",
            joinColumns = @JoinColumn(name = "travelorder_id"),
            inverseJoinColumns = @JoinColumn(name = "reportto_id"))
    private List<ReportTo> reportTos;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "place_travelorder",
            joinColumns = @JoinColumn(name = "travelorder_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    private List<Place> places;


    @Column(name = "last_travel")
    private LocalDate lastTravel;

    public TravelOrder(Long id, LocalDate dateIssued, LocalDate dateDeparture, LocalDate dateReturn, Purpose purpose, Vehicle vehicle, List<ReportTo> reportTos, List<Place> places, LocalDate lastTravel) {
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
            places = new ArrayList<>();
        }
        places.add(place);
    }

    public void removePlace(Place place){
        places.remove(place);
    }

    public void addReportTo(ReportTo reportTo) {
        if (reportTos == null) {
            reportTos = new ArrayList<>();
        }
        reportTos.add(reportTo);
    }


    public void removeReportTo(ReportTo reportTo){
        reportTos.remove(reportTo);
    }
}
