package gov.coateam1.model.place;


import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.TripTicket;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="place")
public class Place {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="building_name")
    private String buildingName;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="barangay")
    private Barangay barangay;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="municipality")
    private Municipality municipality;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="province")
    private Province province;

    @Column(name="default_place")
    private String defaultPlace;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="place_tripticket",
            joinColumns = @JoinColumn(name="place_id"),
            inverseJoinColumns = @JoinColumn(name="tripticket_id"))
    private List<TripTicket> tripTickets;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="place_travelorder",
            joinColumns = @JoinColumn(name="place_id"),
            inverseJoinColumns = @JoinColumn(name="travelorder_id"))
//    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinTable(name="place_travelorder",
//            joinColumns ={
//            @JoinColumn(name="place_id", referencedColumnName = "id")},
//            inverseJoinColumns = {
//            @JoinColumn(name="travelorder_id",referencedColumnName = "id")})
    private List<TravelOrder> travelOrders;

    public Place(Long id, String buildingName, Barangay barangay, Municipality municipality, Province province, String defaultPlace) {
        this.id = id;
        this.buildingName = buildingName;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.defaultPlace = defaultPlace;
    }

    public Place(String defaultPlace) {
        this.defaultPlace  = defaultPlace;
    }

    public void addTravelOrder(TravelOrder travelOrder){
        if(travelOrders == null){
            travelOrders = new ArrayList<>();
        }
        travelOrders.add(travelOrder);
    }

    public void addTripTicket(TripTicket tripTicket){
        if(tripTickets == null){
            tripTickets = new ArrayList<>();
        }
        tripTickets.add(tripTicket);
    }

    public void removeTravelOrder(TravelOrder travelOrder){
        travelOrders.remove(travelOrder);
    }

    public void removeTripTicket(TripTicket tripTicket){
        tripTickets.remove(tripTicket);
    }


}
