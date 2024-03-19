package gov.coateam1.model.place;


import gov.coateam1.model.TravelOrder;
import gov.coateam1.model.trip_ticket.TripTicket;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="barangay_code")
    private Barangay barangay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="municipality_code")
    private Municipality municipality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="province_code")
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_code")
    private Region region;

    @Column(name="default_place")
    private String defaultPlace;

    @ToString.Exclude
    @ManyToMany(mappedBy = "places")
    private Set<TripTicket> tripTickets;


    @ToString.Exclude
    @ManyToMany(mappedBy = "places")
    private Set<TravelOrder> travelOrders;

    public Place(Long id, String buildingName, Barangay barangay, Municipality municipality, Province province, Region region) {
        this.id = id;
        this.buildingName = buildingName;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.region = region;
    }

    public Place(String defaultPlace) {
        this.defaultPlace  = defaultPlace;
    }

}
