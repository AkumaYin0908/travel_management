package gov.coateam1.model;


import gov.coateam1.model.trip_ticket.TripTicket;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="vehicle")
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;

    @Column(name="type")
    private String type;

    @Column(name="plate_no",unique = true)
    private String plateNo;



    @ToString.Exclude
    @OneToMany(mappedBy = "vehicle",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<TravelOrder> travelOrders;

    @ToString.Exclude
    @OneToMany(mappedBy = "vehicle",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<TripTicket> tripTickets;

    public Vehicle(Long id, String brand, String model, String type, String plateNo) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.plateNo = plateNo;
    }


    public void addTravelOrder(TravelOrder travelOrder){
        if(travelOrders == null){
            travelOrders = new ArrayList<>();
        }

        travelOrders.add(travelOrder);
        travelOrder.setVehicle(this);
    }

    public void removeTravelOrder(TravelOrder travelOrder){
        travelOrders.remove(travelOrder);
        travelOrder.setVehicle(null);
    }


}
