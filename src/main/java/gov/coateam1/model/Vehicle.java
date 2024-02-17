package gov.coateam1.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
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

    @Column(name="plate_no")
    private String plateNo;

    @ToString.Exclude
    @OneToMany(mappedBy = "vehicle",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)
    private List<TravelOrder> travelOrders = new ArrayList<>();

    @ToString.Exclude

    @OneToMany(mappedBy = "vehicle",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<TripTicket> tripTickets = new ArrayList<>();

    public Vehicle(Long id, String brand, String model, String type, String plateNo) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.plateNo = plateNo;
    }

    public Vehicle(String brand, String model, String type, String plateNo) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.plateNo = plateNo;
    }
}
