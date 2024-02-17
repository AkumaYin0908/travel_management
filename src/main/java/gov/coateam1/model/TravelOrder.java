package gov.coateam1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.coateam1.model.employee.Employee;
import gov.coateam1.model.place.Place;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "travel_order")
public class TravelOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="employee")
    private Employee employee;

    @Column(name="date_issued")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateIssued;

    @Column(name="date_departure")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateDeparture;

    @Column(name="date_return")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateReturn;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="purpose")
    private Purpose purpose;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="vehicle")
    private Vehicle vehicle;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="reportto_travelorder",
    joinColumns = @JoinColumn(name="travelorder_id"),
    inverseJoinColumns = @JoinColumn(name="reportto_id"))
    private List<ReportTo> reportTos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="place_travelorder",
            joinColumns = @JoinColumn(name="travelorder_id"),
            inverseJoinColumns = @JoinColumn(name="place_id"))
    private List<Place> places = new ArrayList<>();




    public TravelOrder(Long id, Employee employee, LocalDate dateIssued, LocalDate dateDeparture,
                       LocalDate dateReturn, Purpose purpose, Vehicle vehicle) {
        this.id = id;
        this.employee = employee;
        this.dateIssued = dateIssued;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.purpose = purpose;
        this.vehicle = vehicle;
    }

    public TravelOrder(Employee employee, LocalDate dateIssued, LocalDate dateDeparture,
                       LocalDate dateReturn, Purpose purpose, Vehicle vehicle) {
        this.employee = employee;
        this.dateIssued = dateIssued;
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.purpose = purpose;
        this.vehicle = vehicle;
    }


    public void addPlace(Place place){
        if(places == null){
            places=new ArrayList<>();
        }

        places.add(place);
    }


    public void addReportTos(ReportTo reportTo){
        if(reportTos == null){
            reportTos=new ArrayList<>();
        }

        reportTos.add(reportTo);
    }
}
